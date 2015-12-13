package py.com.ventu.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import py.com.ventu.controladores.exceptions.NonexistentEntityException;
import py.com.ventu.entidades.Producto;
import py.com.ventu.util.FiltersObject;

/**
 * @author cpatino
 */
public class ProductosJpaController implements Serializable {

    private EntityManager em;

    public ProductosJpaController(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto productos) throws NonexistentEntityException, Exception {
        try {
            em.getTransaction().begin();

            if (productos.getCodigo() == null) {
                em.persist(productos);
            } else {
                em.merge(productos);
            }

            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }

    public void borrar(Producto productos) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(productos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new NonexistentEntityException("Error al borrar registro. " + ex.getMessage());
        }
    }

    public List<Producto> listarProductos() {
        Query q = null;
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            q = em.createQuery(cq);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return q.getResultList();
    }

    public List<Producto> ListarProductosPorTipoProducto(Integer tipoProducto) {
        String queryString;

        // Hacemos el select de la entidad productos
        queryString = "SELECT o FROM Producto o ";

        // Si el filtro es distinto de null ampliamos la consulta, con el filtro
            queryString = queryString + "WHERE";
            try {
                String atributo = "tipoProducto.id";
                String valor = tipoProducto.toString();
                    int cantidad = Integer.parseInt(valor);
                    queryString = queryString + " o." + atributo + " = " + tipoProducto; //si es un entero traemos lo menos o igual a ese numero

            } catch (Exception e) {

            }

        return em.createQuery(queryString, Producto.class)
                .getResultList();
    
    }

    public Producto buscarProductos(Integer id) {
        try {
            Query q = em.createNamedQuery("Producto.findByCodigo");
            q.setParameter("codigo", id);
            return (Producto) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Producto buscarProductosPorDescripcion(String descripcion) {
        try {
            Query q = em.createNamedQuery("Producto.findByDescripcion");
            q.setParameter("descripcion", descripcion);
            return (Producto) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Long valorActual() {
        try {
            Query q = em.createNativeQuery("SELECT last_value FROM producto_id_seq");
            return (Long) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }

    public List<Producto> filtrar(FiltersObject filtros) {
        String queryString;

        // Hacemos el select de la entidad productos
        queryString = "SELECT o FROM Producto o ";

        // Si el filtro es distinto de null ampliamos la consulta, con el filtro
        if (filtros.getFilters() != null && !filtros.getFilters().isEmpty()) {
            queryString = queryString + "WHERE";
            try {
                String atributo = filtros.getSortField();
                String valor = filtros.getFilters().toString();
                if (atributo.equals("codigo")) {
                    int cantidad = Integer.parseInt(valor);
                    queryString = queryString + " " + atributo + " = " + cantidad; //si es un entero traemos lo menos o igual a ese numero
                } else {
                    valor = "'%" + valor + "%'";
                    queryString = queryString + " o." + atributo + " LIKE " + valor; //si el valor a filtrar es una cadena , traemos la misma cadena o la que le contiene
                }

            } catch (Exception e) {

            }
        }
        //Para el ordenamiento, se agrega el order by a la consulta con el campo que se ordena y la direccion de ordenamiento
        queryString = queryString + " ORDER BY o." + filtros.getSortField();

        return em.createQuery(queryString, Producto.class)
                .setFirstResult(filtros.getFirst())
                .setMaxResults(filtros.getPageSize())
                .getResultList();
    }

    public int filtrarCantidadRegistros(FiltersObject filtros) {
        String queryString;

        // Establece el nombre de la entidad que se esta buscando
        queryString = "SELECT COUNT( o ) FROM Producto o ";

        // Si existen filtros los agrega a la consulta
        if (filtros.getFilters() != null && !filtros.getFilters().isEmpty()) {
            queryString = queryString + "WHERE";
            try {
                String atributo = filtros.getSortField();
                String valor = filtros.getFilters().toString();

                if (atributo.equals("codigo")) {
                    int cantidad = Integer.parseInt(valor);
                    queryString = queryString + " " + atributo + " = " + cantidad;
                } else {
                    valor = "'%" + valor + "%'";
                    queryString = queryString + " o." + atributo + " LIKE " + valor;
                }

            } catch (Exception e) {

            }
        }
        Long result = em.createQuery(queryString, Long.class).getSingleResult();
        // System.out.println("cantidad"+result.intValue());
        return result.intValue();
    }

}
