/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.ventu.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.swing.JOptionPane;
import py.com.ventu.controladores.exceptions.NonexistentEntityException;
import py.com.ventu.entidades.Proveedor;

/**
 *
 * @author Alcides
 */
public class ProveedoresJpaController implements Serializable {

    private EntityManager em;

    public ProveedoresJpaController(EntityManager em) {
        this.em = em;
    }

    public void guardar(Proveedor proveedores) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();

            if (proveedores.getId() == null) {
                em.persist(proveedores);
            } else {
                em.merge(proveedores);
            }

            em.getTransaction().commit();

        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedores.getId();
                if (buscarProveedores(id) == null) {
                    throw new NonexistentEntityException("Error al guardar Registro. El Cliente con ID: " + id + " no existe.");
                }
            }
            throw ex;
        }
    }

    public void borrar(Proveedor proveedores) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(proveedores);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new NonexistentEntityException("Error al borrar registro. " + ex.getMessage());
        }
    }

    public List<Proveedor> listarProveedores() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
            q = em.createQuery(cq);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return q.getResultList();
    }

    public Proveedor buscarProveedores(Integer id) throws NonexistentEntityException {
        try {
            return em.find(Proveedor.class, id);
        } catch (Exception e) {
            throw new NonexistentEntityException("Error al recuperar proveedor. " + e.getMessage());
        }
    }

    public Proveedor buscarPorRuc(String ruc) {
        Proveedor proveedor = null;
        try {
            Query q = em.createNamedQuery("Proveedores.findByRuc");
            q.setParameter("ruc", ruc);
            proveedor = (Proveedor) q.getSingleResult();
        } catch (NoResultException n) {

        } catch (NonUniqueResultException rur) {
            JOptionPane.showMessageDialog(null, "Existe mas de un proveedor con el código: " + ruc + "\n"
                    + "Verifique el código ingresado", "Orden de Compras", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proveedor;
    }

    public Proveedor buscarProveedorPorNombre(String nombre) {
        List<Proveedor> lista;
        try {
            Query q = em.createNamedQuery("Proveedores.findByNombre");
            q.setParameter("nombre", nombre);
            // return (Proveedores) q.getSingleResult();
            lista = q.getResultList();

            if (lista.size() > 0) {
                return lista.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Proveedor> listarProveedores(String filtro) {
        try {
            Query q = em.createQuery("SELECT p FROM Proveedores p WHERE lower(p.ruc) LIKE :filtro OR lower(p.nombre) LIKE :filtro");
            q.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
            return q.getResultList();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
