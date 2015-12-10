package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import py.com.ventu.entidades.CobranzaDet;
import py.com.ventu.entidades.CobranzaDetPK;

/**
 * @author cpatino
 */
public class CobranzaDetJpaController implements Serializable {

    private EntityManager em;

    public CobranzaDetJpaController(EntityManager em) {
        this.em = em;
    }

    public boolean guardar(CobranzaDet cobDet) {
        try {
            em.getTransaction().begin();
            em.persist(cobDet);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el detalle de la cobranza."
                    + "\n" + ex.getMessage(), "Cobranzas", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean editar(CobranzaDet cobDet) {
        try {
            em.getTransaction().begin();
            em.merge(cobDet);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el detalle de la cobranza."
                    + "\n" + ex.getMessage(), "Cobranzas", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void borrar(CobranzaDet cobDet) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(cobDet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el detalle de la cobranza."
                    + "\n" + ex.getMessage(), "Cobranzas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public CobranzaDet buscarCobranzaDet(CobranzaDetPK id) {
        try {
            return em.find(CobranzaDet.class, id);
        } finally {
        }
    }

    public List<CobranzaDet> buscarListaDetalleCobranza(Integer nroRecibo) {
        try {
            Query q = em.createNamedQuery("CobranzaDet.findByNroRecibo");
            q.setParameter("nroRecibo", nroRecibo);
            return (List<CobranzaDet>) q.getResultList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el detalle de la cobranza."
                    + "\n" + ex.getMessage(), "Cobranzas", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
