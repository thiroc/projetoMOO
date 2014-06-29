package projetoMOO.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import projetoMOO.dao.EstadoUFDao;
import projetoMOO.model.Cidade;
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
        return info;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidade> search(@QueryParam("nome") String nome, @QueryParam("longiMax") String longiMax,
            @QueryParam("longiMin") String longiMin, @QueryParam("latMax") String latMax,
            @QueryParam("latMin") String latMin) {
        List<Cidade> query = new ArrayList<Cidade>();
        if (nome != null && !nome.isEmpty())
            query = dao.queryPorNome(nome);
        if (longiMin != null && !longiMin.isEmpty() && longiMax != null && !longiMax.isEmpty())
            query = dao.queryPorLongitude(longiMin, longiMax);
        if (latMin != null && !latMin.isEmpty() && latMax != null && !latMax.isEmpty())
            query = dao.queryPorLatitude(latMin, latMax);
        
        return query;
    }
}
