package py.com.ventu.bootstrap;

import javax.persistence.EntityManager;
import py.com.ventu.controladores.CajaJpaController;
import py.com.ventu.controladores.ProductosJpaController;
import py.com.ventu.controladores.ProveedoresJpaController;
import py.com.ventu.controladores.UsuarioJpaController;
import py.com.ventu.entidades.Caja;
import py.com.ventu.entidades.Usuario;

/**
 * @author carlitox 
 * Metodo que sirve para la carga de valores por defecto del
 * sistema
 */
public class SGFBoostrap {

    private EntityManager em;

    private ProveedoresJpaController proveedoresService;
    private ProductosJpaController productosService;
    private UsuarioJpaController usuariosService;
    private CajaJpaController cajaService;

    public SGFBoostrap(EntityManager em) throws Exception {
        this.em = em;

        iniciarController();
        
        System.out.println("Iniciando Boostrap...");

        // procede a la carga de los valores por defecto
        
        if(usuariosService.listarUsuarios().isEmpty()){
            usuarios();
        }
        
        if(cajaService.listarCaja().isEmpty()){
            cajas();
        }
    }

    private void iniciarController() {
        usuariosService = new UsuarioJpaController(em);
        cajaService = new CajaJpaController(em);
    }

    private void usuarios() throws Exception {
        Usuario usuario = new Usuario();

        usuario.setEsAdmin(true);
        usuario.setContrasena("123");
        usuario.setNombre("Administrador");
        usuario.setUsuario("admin");
        
        usuariosService.guardar(usuario);
    }

    private void cajas() throws Exception {
        Caja caja = new Caja();

        caja.setDescripcion("Caja Venta");
        
        cajaService.guardar(caja);
    }

}
