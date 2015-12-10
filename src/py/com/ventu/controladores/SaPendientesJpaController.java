package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import py.com.ventu.entidades.SaPendientes;

/**
 * @author cpatino
 */
public class SaPendientesJpaController implements Serializable {

    private EntityManager em;

    public SaPendientesJpaController(EntityManager em) {
        this.em = em;
    }

    public boolean guardar(SaPendientes saPendientes) {
        try {
            em.getTransaction().begin();
            em.persist(saPendientes);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar registro de venta crédito."
                    + "\n" + ex.getMessage(), "Venta Crédito", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
    }

    public boolean editar(SaPendientes saPendientes) {
        try {
            em.getTransaction().begin();
            em.merge(saPendientes);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar registro de venta crédito."
                    + "\n" + ex.getMessage(), "Venta Crédito", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
    }

    public void borrar(SaPendientes saPendientes) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(saPendientes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar registro de venta crédito."
                    + "\n" + ex.getMessage(), "Venta Crédito", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public SaPendientes buscarSaPendientes(Integer id) {
        try {
            return em.find(SaPendientes.class, id);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar registro de venta crédito."
                    + "\n" + ex.getMessage(), "Venta Crédito", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return null;
        }
    }

    public List<SaPendientes> listarSaPendientes() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SaPendientes.class));
            q = em.createQuery(cq);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return q.getResultList();
    }
    
    public List<SaPendientes> listarSaPendientes(Integer cliente) {
        try {
            Query q = em.createQuery("SELECT s FROM SaPendientes s WHERE s.cliente = :cliente and s.saldo > 0");
            q.setParameter("cliente", cliente);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public SaPendientes ticketPendiente(Integer ticket) {
        try {
            Query q = em.createNamedQuery("SaPendientes.findByRefTicketPendiente");
            q.setParameter("refTicket", ticket);
            return (SaPendientes) q.getSingleResult();
        } catch (NoResultException nr) {
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
