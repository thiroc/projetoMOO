package projetoMOO.dao;

import org.lightcouch.CouchDbClient;

public class AbstractDAO<T> {

    private final CouchDbClient dbClient = new CouchDbClient();
    
    public void salvar(T entidade) {
    	dbClient.save(entidade);
    }
	
}
