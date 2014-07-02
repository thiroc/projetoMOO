package projetoMOO.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import projetoMOO.dao.EstadoUFDao;
import projetoMOO.model.Info;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/moo")
public class MooResource {
    
    private final EstadoUFDao dao = new EstadoUFDao();
    
    @GET
    @Path("/estado")
    @Produces(MediaType.APPLICATION_JSON)
    public Info pesquisarPorEstado(@QueryParam("estado") String estado) {
        Info info = new Info();
        info.setEstado(estado);
        info.setnOcorrencias(dao.getNOcorrencias(estado));
        info.setTipoOcorrencias(dao.getTipoOcorrencias(estado));
        info.setnPostos(dao.getNPostos(estado));
        return info;
    }
}
