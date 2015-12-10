
package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.JOptionPane;
import py.com.ventu.controladores.exceptions.NonexistentEntityException;
import py.com.ventu.entidades.Usuario;

/**
 * @author cpatino
 */
public class UsuarioJpaController implements Serializable {

    private final EntityManager em;
    
    public UsuarioJpaController(EntityManager em) {
        this.em = em;
    }
    
    public void guardar(Usuario usuarios) throws NonexistentEntityException, Exception {
        try {
            
            if (usuarios.getId() == null) {
                em.getTransaction().begin();
                em.persist(usuarios);
                em.getTransaction().commit();
            } else {
                em.getTransaction().begin();
                em.merge(usuarios);
                em.getTransaction().commit();
            }

        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el registro."
                    + "\n" + ex.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public void borrar(Usuario usuario) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
           /* JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el registro."
                    + "\n" + ex.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public List<Usuario> listarUsuarios() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            q = em.createQuery(cq);
            
            return q.getResultList();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar los registros."
                    + "\n" + ex.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }

    public Usuario buscarUsuarios(Integer id) {
        try {
            return em.find(Usuario.class, id);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar el registro."
                    + "\n" + ex.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }
    
    public Usuario buscarUsuariosPorUsuario(String username) {
        try {
            Query q = em.createNamedQuery("Usuario.findByUsuario");
            q.setParameter("username", username);
            return (Usuario) q.getSingleResult();
        } catch (Exception ex) {
            //em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar el registro."
                    + "\n" + ex.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
