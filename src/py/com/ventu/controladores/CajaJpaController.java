
package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import py.com.ventu.entidades.Caja;

/**
 * @author cpatino
 */
public class CajaJpaController implements Serializable {

    private EntityManager em;
    
    public CajaJpaController(EntityManager em) {
        this.em = em;
    }
    
    public void guardar(Caja caja) throws Exception {
        try {
            if (caja.getId() == null) {
                em.getTransaction().begin();
                em.persist(caja);
                em.getTransaction().commit();
            } else {
                em.getTransaction().begin();
                em.merge(caja);
                em.getTransaction().commit();
            }
            
        } catch (Exception ex) {
            em.getTransaction().rollback();
           /* JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el registro."
                    + "\n" + ex.getMessage(), "Caja", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public void borrar(Caja caja) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(caja);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
           /* JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el registro."
                    + "\n" + ex.getMessage(), "Caja", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public List<Caja> listarCaja() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caja.class));
            q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
           /* JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar lista de registros."
                    + "\n" + ex.getMessage(), "Caja", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
        
    }
    
    public Caja buscarCaja(Integer id) {
        try {
            return em.find(Caja.class, id);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al buscar registro."
                    + "\n" + ex.getMessage(), "Caja", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }
    
    public Caja buscarCajaPorNombre(String descripcion) {        
        try {            
            Query q = em.createNamedQuery("Caja.findByNombre");
            q.setParameter("descripcion", descripcion);
            return (Caja) q.getSingleResult();
        } catch (Exception ex) {            
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al buscar registro."
                    + "\n" + ex.getMessage(), "Caja", JOptionPane.ERROR_MESSAGE);*/
            return null;
        } 
    }
}

