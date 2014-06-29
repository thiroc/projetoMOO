package projetoMOO.tradutorcsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projetoMOO.model.Cidade;
import projetoMOO.model.Ocorrencia;

public class OcorrenciasLeitorCsv {
    
    public static void main(String[] args) {
        OcorrenciasLeitorCsv leitor = new OcorrenciasLeitorCsv();
        leitor.lerArquivos();
    }
    
    public void lerArquivos() {
        
        try {
            // lerUfCsv(tabelaObjetosJson,
            // "./arquivosCSV/uf.csv");
            List<File> ocorrenciasCSV = new ArrayList<File>();
            
            lerCidade("./arquivosCSV/municipio.csv");
            
            ocorrenciasCSV = listarArquivosOcorrencias("./arquivosCSV");
            // lerUnidadePRF(tabelaObjetosJson,
            // "./arquivosCSV/unidadeoperacional.csv");
            
            for (File f : ocorrenciasCSV) {
                lerNumOcorrencias(f);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param string
     */
    private void lerCidade(String arquivo) {
        File arquivoCSV = new File(arquivo);
        if (arquivoCSV.exists()) {
            ReadCSV readCSV = new ReadCSV();
            readCSV.lerCSV(arquivoCSV, Cidade.class);
        }
    }
    
    private void lerNumOcorrencias(File arquivoCSV) throws Exception {
        if (arquivoCSV.exists()) {
            ReadCSV readCSV = new ReadCSV();
            readCSV.lerCSV(arquivoCSV, Ocorrencia.class);
        }
        
    }
    
    private void lerUfCsv(HashMap<String, Ocorrencia> tabelaObjetosJson, String arquivo) throws Exception {
        
        String lineRead;
        
        File file = new File(arquivo);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        br.readLine(); // Lê a primeira linha com
                       // o nome das colunas
        while ((lineRead = br.readLine()) != null) {
            
            String[] colunas = lineRead.split(",");
            
            Ocorrencia objJSON = new Ocorrencia();
            
            // objJSON.setAbreviatura(colunas[0]);
            // objJSON.setNome(colunas[1]);
            // tabelaObjetosJson.put(objJSON.getAbreviatura(),
            // objJSON);
            
        }
        
        br.close();
    }
    
    private void lerUnidadePRF(HashMap<String, projetoMOO.model.Ocorrencia> tabelaObjetosJson, String arquivo)
            throws Exception {
        
        String lineRead;
        
        File file = new File(arquivo);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        br.readLine(); // Lê a primeira linha com
                       // o nome das colunas
        while ((lineRead = br.readLine()) != null) {
            
            String[] colunas = lineRead.split(",");
            String[] siglaSplit = colunas[3].split("/");
            
            if (tabelaObjetosJson.containsKey(siglaSplit[1])) {
                int counter;
                
                // counter =
                // tabelaObjetosJson.get(siglaSplit[1]).getNumUnidadesPrf();
                // counter += 1;
                //
                // tabelaObjetosJson.get(siglaSplit[1]).setNumUnidadesPrf(counter);
            }
        }
        
        br.close();
    }
    
    public List<File> listarArquivosOcorrencias(String directoryName) {
        File directory = new File(directoryName);
        List<File> ocorrencias = new ArrayList<File>();
        // Recupera todos arquivos de um diretório
        File[] fList = directory.listFiles();
        
        for (File file : fList) {
            if (file.getName().contains("ocorrencia_")) {
                ocorrencias.add(file);
            }
        }
        return ocorrencias;
    }
}
