package projetoMOO.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lightcouch.CouchDbClient;
import org.lightcouch.DesignDocument;
import org.lightcouch.DesignDocument.MapReduce;
import org.lightcouch.Response;
import org.lightcouch.View;

import projetoMOO.model.Cidade;
import projetoMOO.model.TipoOcorrencia;

public class EstadoUFDao {
    
    List<Cidade>                bd       = Arrays.asList(new Cidade[] { new Cidade("aaa"), new Cidade("aca"),
            new Cidade("abb")           });
    
    private final CouchDbClient dbClient = new CouchDbClient();
    
    /**
     * 
     */
    
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
        designDocument.setViews(view);
        
        dbClient.design().synchronizeWithDb(designDocument);
        
    }
    
    public Cidade buscarCidade(Integer a) {
        List<Cidade> cidade = dbClient.view("moo/cidade").key(a).includeDocs(true).query(Cidade.class);
        return cidade.get(0);
    }
    
    /**
     * @param estado
     * @return
     */
    public Integer getNOcorrencias(String estado) {
        int queryForInt = dbClient.view("moo/nOcorrencias").reduce(true).key(estado).queryForInt();
        return queryForInt;
    }
    
    /**
     * @param estado
     * @return
     */
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
    
    /**
     * @param latMin
     * @param latMax
     * @return
     */
    public List<Cidade> queryPorLatitude(String min, String max) {
        List<Cidade> query = dbClient.view("latitude/latitude").startKey(new Double(min)).endKey(new Double(max))
                .includeDocs(true).query(Cidade.class);
        return query;
    }
    
    /**
     * @param min
     * @param max
     * @return
     */
    public List<Cidade> queryPorLongitude(String min, String max) {
        List<Cidade> query = dbClient.view("longitude/longitude").startKey(new Double(min)).endKey(new Double(max))
                .includeDocs(true).query(Cidade.class);
        
        return query;
    }
    
    public List<Cidade> queryPorNome(String nome) {
        // TODO fazer uma query de verdade usando
        // CouchDB
        
        List<Cidade> query = new ArrayList<Cidade>();
        for (Cidade m : bd) {
            if (m.getNome().contains(nome)) {
                query.add(m);
            }
        }
        
        return query;
    }
    
    public void salvar(Cidade cidade) {
        Response r = dbClient.save(cidade);
        r.getId();
    }
    
    /* cidade = dbClient.find(Cidade.class,
     * "doc-id");
     * 
     * dbClient.update(cidade);
     * dbClient.remove(cidade);
     * 
     * boolean b = dbClient.contains("doc-id");
     * 
     * attachments
     * dbClient.saveAttachment(inputStream,
     * "photo.jpg", "image/jpg"); InputStream in =
     * dbClient.find("doc-id/photo.jpg");
     * 
     * design documents on local .js files
     * dbClient.syncDesignDocsWithDb();
     * 
     * Views List<Cidade> list =
     * dbClient.view("example/view"
     * ).key("key").includeDocs
     * (true).query(Cidade.class);
     * 
     * long count =
     * dbClient.view("example/by_tag")
     * .key("couchdb").queryForLong();
     * 
     * View paging Page<Cidade> page =
     * dbClient.view
     * ("example/view").queryPage(...);
     * 
     * update handlers String output =
     * dbClient.invokeUpdateHandler
     * ("example/update", "doc-id", query);
     * 
     * database
     * dbClient.context().createDB("new-db");
     * dbClient.context().compact();
     * 
     * replication ReplicationResult replication =
     * dbClient.replication()
     * .source("source-db").target("target-db")
     * .createTarget(true) .trigger();
     * 
     * Replicator replicator =
     * dbClient.replicator()
     * .source("source-db").target("target-db")
     * .filter("example/filter")
     * .continuous(true); Response r =
     * replicator.save(); saves to _replicator
     * database
     * 
     * Change notifications Changes changes =
     * dbClient.changes() .includeDocs(true)
     * .heartBeat(30000) .continuousChanges();
     * 
     * while (changes.hasNext()) {
     * ChangesResult.Row feed = changes.next(); } */
}
