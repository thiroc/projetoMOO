package projetoMOO.rest;

import javax.servlet.ServletException;

import projetoMOO.tradutorcsv.OcorrenciasLeitorCsv;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class MooContainer extends ServletContainer {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        super.init();
        // popularBanco();
    }
    
    private void popularBanco() {
        try {
            OcorrenciasLeitorCsv ocorrencias = new OcorrenciasLeitorCsv();
            
            ocorrencias.lerArquivos();
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
        
    }
    
}
