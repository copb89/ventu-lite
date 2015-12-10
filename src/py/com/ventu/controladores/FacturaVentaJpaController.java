package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import py.com.ventu.entidades.FacturaVenta;

/**
 * @author cpatino
 */
public class FacturaVentaJpaController implements Serializable {

    private EntityManager em;

    public FacturaVentaJpaController(EntityManager em) {
        this.em = em;
    }

    public boolean guardar(FacturaVenta factura) throws Exception {
        try {
            
            em.getTransaction().begin();
            em.persist(factura);
            em.getTransaction().commit();

            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar factura de venta."
                    + "\n" + ex.getMessage(), "Ticket Venta", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean editar(FacturaVenta factura) {
        try {
            em.getTransaction().begin();
            em.merge(factura);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar la factura de venta."
                    + "\n" + ex.getMessage(), "Ticket Venta", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public List<FacturaVenta> listarVentas() {
        Query q = null;
        try {
            q = em.createQuery("FROM FacturaVenta fv ORDER BY fv.ticket ASC");
            q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return q.getResultList();
    }

    public FacturaVenta findFacturaVenta(Integer id) {
        try {
            return em.find(FacturaVenta.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public FacturaVenta buscarPorTicket(Integer ticket) {
        try {
            Query q = em.createNamedQuery("FacturaVenta.findByTicket");
            q.setParameter("ticket", ticket);
            return (FacturaVenta) q.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Long valorActual() {
        try {
            Query q = em.createNativeQuery("SELECT last_value FROM factura_venta_nro_ticket_seq");
            return (Long) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }
}
