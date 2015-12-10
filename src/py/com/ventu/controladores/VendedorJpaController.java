
package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import py.com.ventu.entidades.Vendedor;

/**
 *
 * @author cpatino
 */
public class VendedorJpaController implements Serializable {

    private EntityManager em;
    
    public VendedorJpaController(EntityManager em) {
        this.em = em;
    }
    
    public void guardar(Vendedor vendedores) throws Exception {
        try {
            
            if (vendedores.getId() == null) {
                em.getTransaction().begin();
                em.persist(vendedores);
                em.getTransaction().commit();
            } else {
                em.getTransaction().begin();
                em.merge(vendedores);
                em.getTransaction().commit();
            }

        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el registro."
                    + "\n" + ex.getMessage(), "Vendedor", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public void borrar(Vendedor vendedores) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(vendedores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el registro."
                    + "\n" + ex.getMessage(), "Vendedor", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public List<Vendedor> listarVendedores() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vendedor.class));
            q = em.createQuery(cq);
            
            return q.getResultList();
        } catch (Exception ex) {
           /* JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar registros."
                    + "\n" + ex.getMessage(), "Vendedor", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }

    public Vendedor buscarVendedores(Integer id) {
        try {
            return em.find(Vendedor.class, id);
         } catch (Exception ex) {
           /* JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar registro."
                    + "\n" + ex.getMessage(), "Vendedor", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }  
}
