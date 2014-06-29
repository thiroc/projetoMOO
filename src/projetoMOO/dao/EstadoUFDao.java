package projetoMOO.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;

import projetoMOO.model.Cidade;

public class EstadoUFDao {
    
    List<Cidade>                bd       = Arrays.asList(new Cidade[] { new Cidade("aaa"), new Cidade("aca"),
            new Cidade("abb")           });
    
    private final CouchDbClient dbClient = new CouchDbClient();
    
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
