
package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entities.Ciudad;
import co.edu.sena.adsi.jpa.entities.Ciudad_;
import co.edu.sena.adsi.jpa.entities.Departamento;
import co.edu.sena.adsi.jpa.entities.TipoDocumento;
import co.edu.sena.adsi.jpa.entities.Usuario;
import co.edu.sena.adsi.jpa.entities.Usuario_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author ruberr
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "co.edu.sena.adsi_AdsiCompleto2017_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    /**
     * Busca usuario por email
     *
     * @param email
     * @return Usuario
     */
    public Usuario findUsuarioByEmail(String email) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.where(cb.equal(usuario.get(Usuario_.email), email));
        TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    /**
     * Busca usuario por numDocumento
     *
     * @param numDocumento
     * @return Usuario
     */
    public Usuario findUsuarioByNumDocumento(String numDocumento) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq.where(cb.equal(usuario.get(Usuario_.numDocumento), numDocumento));
        TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
        try {
            return (Usuario) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
    public List<Usuario> findUsers(Integer idUsuario, String sexo, Boolean activo,
            String numDocumento, String email, Integer idCiudad, Integer idDepartamento,
            Integer idTipoDocumento){
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        
        Predicate data = cb.conjunction();
        
        if(idUsuario != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.id), idUsuario));
        }
        
        if(sexo != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.sexo), sexo));
        }
        
        if(activo != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.activo), activo));
        }
        
        if(numDocumento != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.numDocumento), numDocumento));
        }
        
        if(email != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.email), email));
        }
        
        if(idCiudad != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.ciudad), new Ciudad(idCiudad)));
        }
        
        if(idDepartamento != null){
            Join<Usuario, Ciudad> joinCiudad = usuario.join(Usuario_.ciudad);
            data = cb.and(data, cb.equal(joinCiudad.get(Ciudad_.departamento), new Departamento(idDepartamento)));
        }
        
        if(idTipoDocumento != null){
            data = cb.and(data, cb.equal(usuario.get(Usuario_.tipoDocumento), new TipoDocumento(idTipoDocumento)));
        }
        
        cq.where(data);
        cq.orderBy(cb.asc(usuario.get(Usuario_.apellidos)));
        TypedQuery<Usuario> tq = em.createQuery(cq);
        
        try {
            return tq.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
