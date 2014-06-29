package projetoMOO.tradutorcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import projetoMOO.EstadoUF;

public class OcorrenciasLeitorCsv implements LeitorCsv {

	@Override
	public ArrayList<EstadoUF> lerArquivos() {
	
		HashMap<String,EstadoUF> tabelaObjetosJson = new HashMap<String,EstadoUF>();
		ArrayList<File> ocorrenciasCSV = new ArrayList<File>();
		
		try {
			lerUfCsv(tabelaObjetosJson, "./arquivosCSV/uf.csv");
			
			lerUnidadePRF(tabelaObjetosJson, "./arquivosCSV/unidadeoperacional.csv");
			
			listarArquivosOcorrencias(ocorrenciasCSV, "./arquivosCSV");
			
			for (File f : ocorrenciasCSV ) {
				lerNumOcorrencias(tabelaObjetosJson, f);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private void lerUfCsv( 
			HashMap<String, EstadoUF> tabelaObjetosJson, String arquivo) throws Exception {
		
		
		String lineRead; 
		
		File file = new File(arquivo);
		FileReader fr = new FileReader(file);
    	BufferedReader br = new BufferedReader(fr);
    	
    	br.readLine(); // Lê a primeira linha com o nome das colunas
    	while ( (lineRead = br.readLine()) != null ) {
    		
    		String[] colunas = lineRead.split(";");
    		
    		EstadoUF objJSON = new EstadoUF();
    		
    		objJSON.setAbreviatura(colunas[0]);
    		objJSON.setNome(colunas[1]);
    		tabelaObjetosJson.put(objJSON.getAbreviatura(),objJSON);
    		
    	}
    	
    	br.close();
	}

	private void lerUnidadePRF(
			HashMap<String, EstadoUF> tabelaObjetosJson, String arquivo) throws Exception {
		
		String lineRead;
		
		File file = new File(arquivo);
		FileReader fr = new FileReader(file);
    	BufferedReader br = new BufferedReader(fr);
    	
    	br.readLine(); // Lê a primeira linha com o nome das colunas
    	while ( (lineRead = br.readLine()) != null ) {
    		
    		String[] colunas = lineRead.split(";");
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
	
	public void listarArquivosOcorrencias(
			ArrayList<File> ocorrencias, String directoryName){
        File directory = new File(directoryName);

        //Recupera todos arquivos de um diretório
        File[] fList = directory.listFiles();

        for (File file : fList){
            if ( file.getName().contains("ocorrencia_") )
            	ocorrencias.add(file);
        }
    }
	
	private void lerNumOcorrencias(
			HashMap<String, EstadoUF> tabelaObjetosJson, File arquivoOcorrencia) throws Exception {
			
		FileReader frOcorrencia = new FileReader(arquivoOcorrencia);
    	BufferedReader brOcorrencia = new BufferedReader(frOcorrencia);
    	String lineReadOcorrencia;
    	
    	File arquivoLocal = new File("./arquivosCSV/localbr.csv");
    	FileReader frLocal = new FileReader(arquivoLocal);
    	BufferedReader brLocal = new BufferedReader(frLocal);
    	String lineReadLocal;
    	
    	brOcorrencia.readLine(); // Lê a primeira linha com o nome das colunas
    	brLocal.readLine();
    	while ( (lineReadOcorrencia = brOcorrencia.readLine()) != null ) {
    		s
    		String[] colunasOcorrencia = lineReadOcorrencia.split(";");
    		
    		while( (lineReadLocal = brLocal.readLine()) != null ){
    		
    			String[] colunasLocal = lineReadLocal.split(";");
    			
    			if( colunasOcorrencia[1].equals(colunasLocal[0]) &&
    					tabelaObjetosJson.containsKey(colunasLocal[1]) ) {
    				
    				int numOcorrencias;
        			int numOcorrenciasTipo[];
        			
        			numOcorrencias = tabelaObjetosJson.get(colunasLocal[1]).getNumUnidadesPrf();
        			numOcorrencias += 1;
        			tabelaObjetosJson.get(colunasLocal[1]).setNumOcorrencias(numOcorrencias);
        			
        			numOcorrenciasTipo = tabelaObjetosJson.get(colunasLocal[1]).getNumOcorrenciasTipo();
        			//Porque os tipos estão definidos de 1 a 9 em vez de 0 a 8, subtrai 1 do índice do vetor
        			numOcorrenciasTipo[ Integer.parseInt(colunasOcorrencia[7]) - 1] += 1;
        			tabelaObjetosJson.get(colunasLocal[1]).setNumOcorrenciasTipo(numOcorrenciasTipo);
    			}
    		}
    		
    	}
    	
    	brLocal.close();
    	brOcorrencia.close();
	}
}
