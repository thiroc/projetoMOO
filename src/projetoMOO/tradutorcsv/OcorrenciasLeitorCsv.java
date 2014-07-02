package projetoMOO.tradutorcsv;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import projetoMOO.model.Cidade;
import projetoMOO.model.Ocorrencia;
import projetoMOO.model.Posto;

public class OcorrenciasLeitorCsv {
    
    public static void main(String[] args) {
        OcorrenciasLeitorCsv leitor = new OcorrenciasLeitorCsv();
        leitor.lerArquivos();
    }
    
    public void lerArquivos() {
        
        try {
            List<File> ocorrenciasCSV = new ArrayList<File>();
            
            lerPosto("./arquivosCSV/unidadeoperacional.csv");
            lerCidade("./arquivosCSV/municipio.csv");
            
            ocorrenciasCSV = listarArquivosOcorrencias("./arquivosCSV");
            
            for (File f : ocorrenciasCSV) {
                lerOcorrencia(f);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @param string
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    private void lerCidade(String arquivo) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        File arquivoCSV = new File(arquivo);
        if (arquivoCSV.exists()) {
            System.out.println("cidade");
            ReadCSV readCSV = new ReadCSV();
            readCSV.lerCSV(arquivoCSV, Cidade.class);
        }
    }
    
    private void lerOcorrencia(File arquivoCSV) throws Exception {
        if (arquivoCSV.exists()) {
            System.out.println("ocorrencia");
            ReadCSV readCSV = new ReadCSV();
            readCSV.lerCSV(arquivoCSV, Ocorrencia.class);
        }
        
    }
    
    private void lerPosto(String arquivo) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        File arquivoCSV = new File(arquivo);
        if (arquivoCSV.exists()) {
            
            ReadCSV readCSV = new ReadCSV();
            readCSV.lerCSV(arquivoCSV, Posto.class);
        }
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
