
package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.Pelicula;
import co.edu.sena.adsi.jpa.sessions.PeliculaFacade;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bratc
 */
@Path("peliculas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PeliculasREST {
    

    @EJB
    private PeliculaFacade peliculasEJB;
    
    /**
     * Obtiene todos los likes
     * @return lista de likes
     */
    @GET
    public List<Pelicula> findAll(){
        return peliculasEJB.findAll();
    }

    /**
     * Busca pelicula por su id
     * @param id
     * @return pelicula
     */
    @GET
    @Path("{id}")
    public Pelicula findById(@PathParam("id") String id){
        return peliculasEJB.find(id);
    }
    
    /**
     * Crear un pelicula
     * @param pelicula 
     */
    @POST
    public void create(Pelicula pelicula){
        peliculasEJB.create(pelicula);
    }
    
    /**
     * Edita un pelicula
     * @param id
     * @param pais 
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") String id, Pelicula pelicula){
        peliculasEJB.edit(pelicula);
    }
}
