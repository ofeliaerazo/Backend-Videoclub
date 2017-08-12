
package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entities.EmailApp;
import co.edu.sena.adsi.jpa.entities.EmailApp_;
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
 * @author ruber28
 */
@Stateless
public class EmailAppFacade extends AbstractFacade<EmailApp> {

    @PersistenceContext(unitName = "co.edu.sena.adsi_AdsiCompleto2017_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmailAppFacade() {
        super(EmailApp.class);
    }
    
    /**
     * Busca configuración para envío de email
     * @param idEmail
     * @return EmailApp
     */
    public EmailApp findConfigEmail(String idEmail) {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EmailApp> cq = cb.createQuery(EmailApp.class);
        Root<EmailApp> emailApp = cq.from(EmailApp.class);
        cq.where(cb.and(cb.equal(emailApp.get(EmailApp_.id), idEmail)),
                cb.equal(emailApp.get(EmailApp_.estado), true));
        TypedQuery<EmailApp> q = getEntityManager().createQuery(cq);
        try {
            return (EmailApp) q.getSingleResult();
        } catch (NonUniqueResultException ex) {
            throw ex;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
