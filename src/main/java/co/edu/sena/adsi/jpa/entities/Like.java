
package co.edu.sena.adsi.jpa.entities;

import co.edu.sena.adsi.jpa.entities.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bratc
 */
@Entity
@Table(name = "likes")
@XmlRootElement
public class Like implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "likes")
    private Boolean likes;
    
  
    @JoinColumn(name = "idPelicula", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pelicula idPelicula;
    
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    
   

    public Like() {
    }

    public Like(Integer id) {
        this.id = id;
    }

    public Like(Integer id, String nombre) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLikes() {
        return likes;
    }

    public void setLikes(Boolean likes) {
        this.likes = likes;
    }

   
    public Pelicula getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Pelicula idPelicula) {
        this.idPelicula =  idPelicula;
    }


    
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

   
    
    @Override
    public String toString() {
        return "co.edu.sena.adsi.jpa.entities.Likes[ id=" + id + " ]";
    }
    
}
