package py.com.ventu.controladores;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import py.com.ventu.entidades.Configuraciones;

/**
 * @author cpatino
 */
public class ConfiguracionesJpaController implements Serializable {

    private EntityManager em;

    public ConfiguracionesJpaController(EntityManager em) {
        this.em = em;
    }

    public void guardar(Configuraciones conf) throws Exception {
        try {

            if (conf.getId() == null) {
                em.getTransaction().begin();
               em.persist(conf);
                em.getTransaction().commit();
            } else {
                em.getTransaction().begin();
                em.merge(conf);
                em.getTransaction().commit();
            }

        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el registro."
                    + "\n" + ex.getMessage(), "Configuraciones", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public void borrar(Configuraciones conf) {
        try {
            em.getTransaction().begin();
            em.remove(conf);
            em.getTransaction().commit();
 
        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el registo."
                    + "\n" + ex.getMessage(), "Configuraciones", JOptionPane.ERROR_MESSAGE);*/
        }
    }

    public Configuraciones  buscarConfiguraciones(Integer id) {
        try {
            return em.find(Configuraciones.class, id);
        } catch (Exception ex) {
            em.getTransaction().rollback();
            /*JOptionPane.showMessageDialog(null, "Ocurrió un error al borrar el registo."
                    + "\n" + ex.getMessage(), "Configuraciones", JOptionPane.ERROR_MESSAGE);*/
            return null;
        }
    }

}
