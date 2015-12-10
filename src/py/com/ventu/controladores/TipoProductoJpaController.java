
package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import py.com.ventu.entidades.TipoProducto;

/**
 * @author Carlos Patiño
 */
public class TipoProductoJpaController implements Serializable {

    private EntityManager em;
    
    public TipoProductoJpaController(EntityManager em) {
        this.em = em;
    }
    
    public void guardar(TipoProducto tipoProductos) throws Exception {
        try {
           
            if (tipoProductos.getId() == null) {
                em.getTransaction().begin();
                em.persist(tipoProductos);
                em.getTransaction().commit();
            } else {
                em.getTransaction().begin();
                em.merge(tipoProductos);
                em.getTransaction().commit();
            }

        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el registro."
                    + "\n" + ex.getMessage(), "Tipo Producto", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public void borrar(TipoProducto tipoProductos) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(tipoProductos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el registro."
                    + "\n" + ex.getMessage(), "Tipo Producto", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public List<TipoProducto> listarTipoProductos() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoProducto.class));
            q = em.createQuery(cq);
            
             return q.getResultList();
        } catch (Exception ex) {
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar registros."
                    + "\n" + ex.getMessage(), "Tipo Producto", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }

    public TipoProducto buscarTipoProductos(Integer id) {
        try {
            return em.find(TipoProducto.class, id);
        } catch (Exception ex) {
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar el registro."
                    + "\n" + ex.getMessage(), "Tipo Producto", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }
    
    public TipoProducto buscarTipoProductosPorDescripcion(String descripcion) {        
        try {            
            Query q = em.createNamedQuery("TipoProductos.findByDescripcion");
            q.setParameter("descripcion", descripcion);
            return (TipoProducto) q.getSingleResult();
        } catch (Exception ex) {
           /* JOptionPane.showMessageDialog(null, "Ocurrió un error al buscar el registro."
                    + "\n" + ex.getMessage(), "Tipo Producto", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }
}
