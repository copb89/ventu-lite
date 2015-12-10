package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import py.com.ventu.entidades.FacturaVentaDet;
import py.com.ventu.entidades.FacturaVentaDetPK;

/**
 * @author cpatino
 */
public class FacturaVentaDetJpaController implements Serializable {

    private EntityManager em;

    public FacturaVentaDetJpaController(EntityManager em) {
        this.em = em;
    }

    public boolean guardar(FacturaVentaDet factDet) {
        try {
            em.getTransaction().begin();
            em.persist(factDet);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el detalle de la venta."
                    + "\n" + ex.getMessage(), "Ticket Venta", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean editar(FacturaVentaDet factDet) {
        try {
            em.getTransaction().begin();
            em.merge(factDet);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el detalle de la venta."
                    + "\n" + ex.getMessage(),  "Ticket Venta", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public FacturaVentaDet findFacturaVentaDet(FacturaVentaDetPK id) {
        try {
            return em.find(FacturaVentaDet.class, id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al buscar el detalle de la venta."
                    + "\n" + ex.getMessage(), "Ticket Venta", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<FacturaVentaDet> buscarListaDetalleVenta(Integer nroTransVta) {
        try {
            Query q = em.createNamedQuery("FacturaVentaDet.findByNroTrans");
            q.setParameter("nroTrans", nroTransVta);
            return (List<FacturaVentaDet>) q.getResultList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el detalle de la venta."
                    + "\n" + ex.getMessage(), "Ticket Venta", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
