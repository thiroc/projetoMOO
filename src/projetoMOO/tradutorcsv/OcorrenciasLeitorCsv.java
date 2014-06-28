package projetoMOO.tradutorcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import projetoMOO.ObjetoJSON;

public class OcorrenciasLeitorCsv implements LeitorCsv {

	@Override
	public ArrayList<ObjetoJSON> lerArquivos() {
	
		HashMap<String,ObjetoJSON> tabelaObjetosJson = new HashMap<String,ObjetoJSON>();
		
		try {
			lerUfCsv(tabelaObjetosJson, "./arquivosCSV/uf.csv");
			
			lerUnidadePRF(tabelaObjetosJson, "./arquivosCSV/unidadeoperacional.csv");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void lerUfCsv( 
			HashMap<String, ObjetoJSON> tabelaObjetosJson, String arquivo) throws Exception {
		
		
		String lineRead; 
		
		File file = new File(arquivo);
		FileReader fr = new FileReader(file);
    	BufferedReader br = new BufferedReader(fr);
    	
    	br.readLine(); // Lê a primeira linha com o nome das colunas
    	while ( (lineRead = br.readLine()) != null ) {
    		
    		String[] colunas = lineRead.split(",");
    		
    		ObjetoJSON objJSON = new ObjetoJSON();
    		
    		objJSON.setAbreviatura(colunas[0]);
    		objJSON.setNome(colunas[1]);
    		tabelaObjetosJson.put(objJSON.getAbreviatura(),objJSON);
    		
    	}
    	
    	br.close();
	}

	private void lerUnidadePRF(
			HashMap<String, ObjetoJSON> tabelaObjetosJson, String arquivo) throws Exception {
		
		String lineRead;
		
		File file = new File(arquivo);
		FileReader fr = new FileReader(file);
    	BufferedReader br = new BufferedReader(fr);
    	
    	br.readLine(); // Lê a primeira linha com o nome das colunas
    	while ( (lineRead = br.readLine()) != null ) {
    		
    		String[] colunas = lineRead.split(",");
    		String[] siglaSplit = colunas[3].split("/");
    		
    		if ( tabelaObjetosJson.containsKey(siglaSplit[1]) ) {
    			int counter;
    			
    			counter = tabelaObjetosJson.get(siglaSplit[1]).getNumUnidadesPrf();
    			counter += 1;
    			
    			tabelaObjetosJson.get(siglaSplit[1]).setNumUnidadesPrf(counter);
    		}
    	}
    	
    	br.close();
	}
}
