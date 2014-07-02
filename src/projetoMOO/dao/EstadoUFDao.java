package projetoMOO.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lightcouch.CouchDbClient;
import org.lightcouch.DesignDocument;
import org.lightcouch.DesignDocument.MapReduce;
import org.lightcouch.View;

import projetoMOO.model.Cidade;
import projetoMOO.model.TipoOcorrencia;

public class EstadoUFDao {
    
    private final CouchDbClient dbClient = new CouchDbClient();
    
    public EstadoUFDao() {
        
        DesignDocument designDocument = new DesignDocument();
        designDocument.setId("_design/moo");
        designDocument.setLanguage("javascript");
        
        MapReduce cidade = new MapReduce();
        StringBuilder cidadeMap = new StringBuilder();
        cidadeMap.append("function(doc){");
        cidadeMap.append("if(doc.tipo == 'cidade'){");
        cidadeMap.append(" emit(doc.codigo,doc);");
        cidadeMap.append("}");
        cidadeMap.append("}");
        cidade.setMap(cidadeMap.toString());
        
        MapReduce posto = new MapReduce();
        StringBuilder postoMap = new StringBuilder();
        postoMap.append("function(doc) {");
        postoMap.append("  if(doc.sigla){");
        postoMap.append("   emit(doc.sigla.estado,1);");
        postoMap.append("  }");
        postoMap.append("}");
        posto.setMap(postoMap.toString());
        posto.setReduce("function(keys, values){ return sum(values);}");
        
        MapReduce nOcorrencias = new MapReduce();
        StringBuilder ocorrenciasMap = new StringBuilder();
        ocorrenciasMap.append("function(doc){    if(doc.cidade && doc.cidade.estado){     ");
        ocorrenciasMap.append(" emit(doc.cidade.estado,1);   } }");
        nOcorrencias.setMap(ocorrenciasMap.toString());
        nOcorrencias.setReduce("function(keys, values){ return sum(values);}");
        
        MapReduce tipoOcorrencias = new MapReduce();
        StringBuilder tipoMap = new StringBuilder();
        tipoMap.append("function(doc){    if(doc.tipoOcorrencia){     ");
        tipoMap.append(" emit([doc.tipoOcorrencia.id,doc.cidade.estado] ,1);   } }");
        tipoOcorrencias.setMap(tipoMap.toString());
        tipoOcorrencias.setReduce("function(keys, values){ return sum(values);}");
        
        Map<String, MapReduce> view = new HashMap<>();
        view.put("cidade", cidade);
        view.put("nOcorrencias", nOcorrencias);
        view.put("tipoOcorrencias", tipoOcorrencias);
        view.put("postos", posto);
        designDocument.setViews(view);
        
        dbClient.design().synchronizeWithDb(designDocument);
        
    }
    
    public Cidade buscarCidade(Integer a) {
        List<Cidade> cidade = dbClient.view("moo/cidade").key(a).includeDocs(true).query(Cidade.class);
        return cidade.get(0);
    }
    
    public Integer getNOcorrencias(String estado) {
        int queryForInt = dbClient.view("moo/nOcorrencias").reduce(true).key(estado).queryForInt();
        return queryForInt;
    }
    
    public Integer getNPostos(String estado) {
        boolean empty = dbClient.view("moo/postos").reduce(true).key(estado).query(Object.class).isEmpty();
        if (empty) {
            return 0;
        }
        
        int queryForInt = dbClient.view("moo/postos").reduce(true).key(estado).queryForInt();
        return queryForInt;
    }
    
    public Map<TipoOcorrencia, Integer> getTipoOcorrencias(String estado) {
        
        Map<TipoOcorrencia, Integer> map = new HashMap<TipoOcorrencia, Integer>();
        
        for (TipoOcorrencia tipo : TipoOcorrencia.values) {
            
            View key = dbClient.view("moo/tipoOcorrencias").reduce(true).key(tipo.getId(), estado);
            boolean query = key.query(Object.class).isEmpty();
            map.put(tipo, query ? 0 : dbClient.view("moo/tipoOcorrencias").reduce(true).key(tipo.getId(), estado)
                    .queryForInt());
        }
        
        return map;
    }
}
