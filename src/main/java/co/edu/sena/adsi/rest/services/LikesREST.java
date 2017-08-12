package co.edu.sena.adsi.rest.services;

import co.edu.sena.adsi.jpa.entities.Like;
import co.edu.sena.adsi.jpa.entities.Pelicula;
import co.edu.sena.adsi.jpa.entities.Usuario;
import co.edu.sena.adsi.jpa.sessions.LikeFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bratc
 */
@Path("likes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LikesREST {

    @EJB
    private LikeFacade likeEJB;

    /**
     * Obtiene todos los likes
     *
     * @return lista de likes
     */
    @GET
    public List<Like> findAll() {
        return likeEJB.findAll();
    }

    /**
     * Busca likes por su id
     *
     * @param id
     * @return like
     */
    @GET
    @Path("{id}")
    public Like findById(@PathParam("id") String id) {
        return likeEJB.find(id);
    }

    /**
     * Crear un like
     *
     * @param like
     */
    @POST
    public void create(Like like) {
        likeEJB.create(like);
    }

    /**
     * Edita un like
     *
     * @param id
     * @param like
     */
    @PUT
    @Path("{id}")
    public void edit(@PathParam("id") String id, Like like) {
        likeEJB.edit(like);
    }

    @PUT
    @Path("updateLike/{usuario}/{pelicula}/{like}")
    public Response actualizarLike(
            @PathParam("like") boolean like,
            @PathParam("pelicula") Integer pelicula,
            @PathParam("usuario") Integer usuario) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String mensaje = "LIKE!";
        
        System.out.println("LIKE");
        System.out.println(like);
        System.out.println("pelicula");
        System.out.println(pelicula);
        System.out.println("USUARIO");
        System.out.println(usuario);
        
        try {
            Like likeEntity = likeEJB.findLikeByUserPelicula(pelicula, usuario);

            if (likeEntity != null) {
                //UPDATE
                likeEntity.setLikes(like);
                likeEJB.edit(likeEntity);
            } else {
                //CREATE
                Like newLike = new Like();
                newLike.setIdPelicula(new Pelicula(pelicula));
                newLike.setIdUsuario(new Usuario(usuario));
                newLike.setLikes(like);
                likeEJB.create(newLike);
            }
            if(!like){
                mensaje = "UNLIKE!";
            }
            return Response.status(Response.Status.CREATED).entity(gson.toJson(mensaje)).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson("Error al like")).build();
        }

    }

}
