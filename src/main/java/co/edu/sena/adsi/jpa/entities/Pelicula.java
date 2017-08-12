package co.edu.sena.adsi.jpa.entities;

import co.edu.sena.adsi.jpa.entities.Like;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ruberr
 */
@Entity
@Table(name = "peliculas")
@XmlRootElement
public class Pelicula implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;

    @Basic(optional = true)
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    
   // private Integer cantidadLike;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPelicula")
    private List<Like> likesList;

    

    public Pelicula() {
    }

    public Pelicula(Integer id) {
        this.id = id;
    }

    public Pelicula(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Like> getLikesList() {
        return likesList;
    }

    public void setLikesList(List<Like> likesList) {
        this.likesList = likesList;
    }
   
    public Integer getCantidadLike() {
        Integer cantidadLike = 0;
        for (Like like : this.likesList) {
            if (like.getLikes()) {
                cantidadLike++;

            }
        }
        System.out.println("CANTIDAD LIKE "+cantidadLike);
        return cantidadLike;
    }
    public void setCantidadLike(Integer cantidadLike) {
        //this.cantidadLike = cantidadLike;
    }
    public Integer getCantidadUnLike(){
        Integer cantidadUnLike = 0;
        for(Like like:this.likesList){
            if(!like.getLikes()){
                cantidadUnLike++;
            }
        }
        return cantidadUnLike;
    }
    
    public void setCantidadUnLike(Integer cantidadLike) {
        //this.cantidadLike = cantidadLike;
    }

    
    @Override
    public String toString() {
        return "co.edu.sena.adsi.jpa.entities.Peliculas[ id=" + id + " ]";
    }

}
