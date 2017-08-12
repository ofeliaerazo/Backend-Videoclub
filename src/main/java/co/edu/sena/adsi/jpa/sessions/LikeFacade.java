/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entities.Like;
import co.edu.sena.adsi.jpa.entities.Like_;
import co.edu.sena.adsi.jpa.entities.Pelicula;
import co.edu.sena.adsi.jpa.entities.Usuario;
import co.edu.sena.adsi.jpa.sessions.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author bratc
 */
@Stateless
public class LikeFacade extends AbstractFacade<Like> {

    @PersistenceContext(unitName = "co.edu.sena.adsi_AdsiCompleto2017_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LikeFacade() {
        super(Like.class);
    }
    
    public Like findLikeByUserPelicula(Integer idPelicula, Integer idUsuario) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Like> cq = cb.createQuery(Like.class);
        Root<Like> likeRoot = cq.from(Like.class);
        cq.where(cb.equal(likeRoot.get(Like_.idPelicula), new Pelicula(idPelicula)),
                cb.equal(likeRoot.get(Like_.idUsuario), new Usuario(idUsuario)));
        TypedQuery<Like> q = getEntityManager().createQuery(cq);
        try {
            return (Like) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
    
}
