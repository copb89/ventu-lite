
package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import py.com.ventu.controladores.exceptions.NonexistentEntityException;
import py.com.ventu.entidades.Cliente;

/**
 * @author cpatino
 */
public class ClientesJpaController implements Serializable {

    private EntityManager em;
    
    public ClientesJpaController(EntityManager em) {
        this.em = em;
    }
    
    public void guardar(Cliente clientes) throws NonexistentEntityException, Exception {
        try {

            if (clientes.getId() == null) {
                em.getTransaction().begin();
                em.persist(clientes);
                em.getTransaction().commit();
            } else {
                em.getTransaction().begin();
                em.merge(clientes);
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void borrar(Cliente clientes) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(clientes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new Exception("Error al borrar registro. " + ex.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            q = em.createQuery(cq);
        } catch (Exception ex) {
            return null;
        }
        return q.getResultList();
    }

    public Cliente buscarClientes(Integer id) {
        try {
            return em.find(Cliente.class, id);
        } catch (Exception ex) {
           // throw new Exception("Error al recuperar cliente. " + ex.getMessage());
             return null;
        }
    }
    
    public Cliente buscarPorNombre(String nombre) {
        Cliente cliente = null;
        try {
            Query q = em.createNamedQuery("Cliente.findByCliente");
            q.setParameter("cliente", nombre);
            cliente = (Cliente) q.getSingleResult();
        } catch (Exception ex) {
            //throw new Exception("Error al recuperar cliente. " + ex.getMessage());
             return null;
        }
        
        return cliente;
    }

    public Cliente buscarPorCi(String ci) {
        Cliente cliente = null;
        try {
            Query q = em.createNamedQuery("Cliente.findByCi");
            q.setParameter("ci", ci);
            cliente = (Cliente) q.getSingleResult();
        } catch (Exception ex) {
           // throw new Exception("Error al recuperar cliente. " + ex.getMessage());
             return null;
        }
        return cliente;
    }

   public Cliente buscarPorLegajo(Integer legajo){
        Cliente cliente = null;
        try {
            Query q = em.createNamedQuery("Cliente.findByLegajo");
            q.setParameter("legajo", legajo);
            cliente = (Cliente) q.getSingleResult();
        } catch (Exception ex) {
            //throw new Exception("Error al recuperar cliente. " + ex.getMessage());
             return null;
        }
        
        return cliente;
    }
}