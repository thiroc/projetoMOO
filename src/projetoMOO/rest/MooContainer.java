package projetoMOO.rest;

import java.io.File;

import javax.servlet.ServletException;

import projetoMOO.InfratorCondutor;
import projetoMOO.csv.ReadCSV;

import com.sun.jersey.spi.container.servlet.ServletContainer;

public class MooContainer extends ServletContainer {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        super.init();
        popularBanco();
    }
    
    private void popularBanco() {
        try {
            // Client client = Client.create();
            //
            // WebResource webResource = client
            // .resource("http://seriesestatisticas.ibge.gov.br/exportador.aspx?arquivo=PD299_RM_ABS.csv&categorias=%22Homem_Mulher%22&localidade=Todas");
            //
            // ClientResponse response =
            // webResource.accept("*/*").get(ClientResponse.class);
            //
            // if (response.getStatus() != 200) {
            // throw new
            // RuntimeException("Failed : HTTP error code : "
            // + response.getStatus());
            // }
            //
            // String output =
            // response.getEntity(String.class);
            /* Accept:text/html,application/xhtml+xml
             * ,
             * application/xml;q=0.9,image/webp,**
             * ;q=0.8
             * Accept-Encoding:gzip,deflate,sdch
             * Accept
             * -Language:pt-BR,pt;q=0.8,en-US
             * ;q=0.6,en;q=0.4,es;q=0.2
             * Connection:keep-alive
             * Host:seriesestatisticas.ibge.gov.br
             * Referer
             * :http://dados.gov.br/dataset/
             * populacao
             * -economicamente-ativa-por-sexo
             * /resource
             * /db50b0ee-c4bf-4ae5-be82-ba116281e0f5
             * User-Agent:Mozilla/5.0 (Windows NT
             * 6.2; WOW64) AppleWebKit/537.36
             * (KHTML, like Gecko)
             * Chrome/35.0.1916.153 Safari/537.36 */
            // build.
            
            //ReadExcel test = new ReadExcel();
            //test.setInputFile("Tabela Municipio Geo.xlsx");
            //test.read();
        	
            ReadCSV reader = new ReadCSV();
            reader.lerCSV(new File("D:\\CiC\\Semestre Atual\\ProjetoMOO\\WORKSPACE\\Teste1\\infracaocondutor-2007 1 semestre.csv"), InfratorCondutor.class);
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
        
    }
    
}