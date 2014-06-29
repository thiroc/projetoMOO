package projetoMOO.dao;

import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;

import projetoMOO.InfratorCondutor;

public class InfratorCondutorDAO {
    
    private final CouchDbClient dbClient = new CouchDbClient();
    
    public void salvar(InfratorCondutor infrator) {
        Response r = dbClient.save(infrator);
        r.getId();
    }

}
