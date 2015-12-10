package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import py.com.ventu.controladores.exceptions.PreexistingEntityException;
import py.com.ventu.entidades.Cobranza;

/**
 * @author cpatino
 */
public class CobranzaJpaController implements Serializable {

    private EntityManager em;

    public CobranzaJpaController(EntityManager em) {
        this.em = em;
    }

    public boolean guardar(Cobranza cobranza) throws PreexistingEntityException, Exception {
        try {
            em.getTransaction().begin();
            em.persist(cobranza);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar cobranza."
                    + "\n" + ex.getMessage(), "Cobranzas", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean editar(Cobranza factura) {
        try {
            em.getTransaction().begin();
            em.merge(factura);
            em.getTransaction().commit();
            return true;

        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el detalle de la cobranza."
                    + "\n" + ex.getMessage(), "Cobranzas", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean borrar(Cobranza factura) {
        try {
            em.getTransaction().begin();
            em.remove(factura);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el detalle de la cobranza."
                    + "\n" + ex.getMessage(), "Cobranzas", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Cobranza buscarCobranza(Integer id) {
        try {
            return em.find(Cobranza.class, id);
        } finally {
        }
    }

    public Long valorActual() {
        try {
            Query q = em.createNativeQuery("SELECT last_value FROM cobranza_nro_trans_seq");
            return (Long) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }

    public List<Cobranza> listarCobranzas() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cobranza.class));
            q = em.createQuery(cq);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return q.getResultList();
    }

    public List<Cobranza> listarCobranzas(String filtro) {
        try {
            Query q = em.createQuery("SELECT c FROM Cobranza c INNER JOIN Clientes cl ON cl.id = c.codCliente "
                    + " WHERE lower(cl.ci) LIKE :filtro OR lower(cl.cliente) LIKE :filtro");
            q.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
            return q.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
