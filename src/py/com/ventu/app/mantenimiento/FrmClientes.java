package py.com.ventu.app.mantenimiento;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_F2;
import static java.awt.event.KeyEvent.VK_F3;
import static java.awt.event.KeyEvent.VK_F5;
import static java.awt.event.KeyEvent.VK_F9;

import java.awt.event.KeyListener;
import java.util.Date;
//import py.com.ventu.app.util.buscadores.FrmBuscar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import py.com.ventu.controladores.ClientesJpaController;
import py.com.ventu.entidades.Cliente;
import py.com.ventu.entidades.Estados;
import py.com.ventu.entidades.Usuario;

/**
 * @author cpatino
 */
public class FrmClientes extends javax.swing.JInternalFrame {

    private EntityManager em;
    
    private ClientesJpaController service;
    
    private Cliente cliente = new Cliente();
    
    private List<Cliente> listaClientes;
    
    private int cantClientes;
    private int posLista;

    private Estados estado;
    private Usuario usuario;

    private JDesktopPane escritorio;
    
    /**
     * Creates new form FrmGrupoCliente
     */
    public FrmClientes(EntityManager em, JDesktopPane dsk, Usuario user) {
        initComponents();
        this.em = em;
        this.escritorio = dsk;
        this.usuario = user;

        service = new ClientesJpaController(em);
        
        
        estado = Estados.CANCELADO;

        keyListener();
        cargarListaRegistros(0);
        desplegarPrimerRegistro();
    }

    public void keyListener() {
        KeyListener listener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                procesarKeyEvent(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        addKeyListener(listener);
        setFocusable(true);
    }

    public void procesarKeyEvent(KeyEvent evt) {

        // F2 - nuevo ingreso
        if (evt.getKeyCode() == VK_F2) {
            nuevo();
        }

        // F3 - editar ingreso
        if (evt.getKeyCode() == VK_F3) {
            editar();
        }

        // F4 - guardar ingreso
        if (evt.getKeyCode() == VK_F3) {
            guardar();
        }

        // F5 - eliminar
        if (evt.getKeyCode() == VK_F5) {
            eliminar();
        }

        // F9 - buscador generico de productos
        if (evt.getKeyCode() == VK_F9) {
            buscadorGenerico();
        }

        // ESC - cancelar operacion
        if (evt.getKeyCode() == VK_ESCAPE) {
            cancelar();
        }
    }

    /**
     * Carga los Campos en base al valor del objeto "Cliente".
     */
    private void cargarCampos() {
        //txtLegajo.setText(cliente.getLegajo().toString());
        txtCliente.setText(cliente.getNombre());
        txtCI.setText(cliente.getCi());
        txtDireccion.setText(cliente.getDireccion());
        txtTelefono.setText(cliente.getTelefono());
        //txtCodEmpresa.setText(cliente.getEmpresa().getId().toString());
        //txtNomEmpresa.setText(cliente.getEmpresa().getDescripcion());
        //txtCodDepartamento.setText(cliente.getDepartamento().getId().toString());
       // txtNomDepartamento.setText(cliente.getDepartamento().getDescripcion());
    }

    /**
     * Inicializa los Campos para ser cargados
     */
    public void inicializarComponentes() {
        txtLegajo.setText("");
        txtCliente.setText("");
        txtCI.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCodEmpresa.setText("");
        txtNomEmpresa.setText("");
        txtCodDepartamento.setText("");
        txtNomDepartamento.setText("");

        desbloquearCampos();
    }

    /**
     * Recupera la lista de registros de la base de datos.
     */
    public void cargarListaRegistros(int busco) {
        listaClientes = service.listarClientes();
        cantClientes = listaClientes.size();

        if (listaClientes.size() > 0) {
            posLista = busco;

            cliente = listaClientes.get(posLista);

            /*txtLegajo.setText(cliente.getLegajo().toString());
            txtCliente.setText(cliente.getNombre());
            txtCI.setText(cliente.getCi());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
            txtCodEmpresa.setText(cliente.getEmpresa().getId().toString());
            txtNomEmpresa.setText(cliente.getEmpresa().getDescripcion());
            txtCodDepartamento.setText(cliente.getDepartamento().getId().toString());
            txtNomDepartamento.setText(cliente.getDepartamento().getDescripcion());
*/
            cargarCampos();
            //En el caso que se encuentre con elementos la tabla , 
            //activa el boton Editar y Eliminar puesto que existe elemento a editar
            cmdEditar.setEnabled(true);
            cmdEliminar.setEnabled(true);
        } else { //En el caso que se encuentre vacia la tabla , 
            //desactiva el boton Editar y Eliminar puesto que no existe elemento a editar
            cmdEditar.setEnabled(false);
            cmdEliminar.setEnabled(false);
        }
    }

    /**
     * Despliega el primer registro de la lista.
     */
    private void desplegarPrimerRegistro() {
        if (listaClientes.size() > 0) {
            posLista = 0;

            cliente = listaClientes.get(posLista);

            /*txtLegajo.setText(cliente.getLegajo().toString());
            txtCI.setText(cliente.getCi());
            txtDireccion.setText(cliente.getDireccion());
            txtTelefono.setText(cliente.getTelefono());
            txtRuc.setText(cliente.getRuc());*/

            cargarCampos();
        }
    }

    /**
     * Desbloquea los campos para ser editados.
     */
    private void desbloquearCampos() {
        if(estado.equals(Estados.NUEVO)){
          txtLegajo.setEditable(true);  
        }
        txtCliente.setEditable(true);
        txtCI.setEditable(true);
        txtDireccion.setEditable(true);
        txtTelefono.setEditable(true);
        txtCodEmpresa.setEditable(true);
        txtCodDepartamento.setEditable(true);
    }
    
    /**
     * Bloquea los campos para ser editados.
     */
    private void bloquearCampos(){    
        txtLegajo.setEditable(false);
        txtCliente.setEditable(false);
        txtCI.setEditable(false);
        txtDireccion.setEditable(false);
        txtTelefono.setEditable(false);
        txtCodEmpresa.setEditable(false);
        txtCodDepartamento.setEditable(false);
    }

    /**
     * Bloquea la utilización de los botones innecesarios durante la carga de un
     * nuevo registro o la edición de uno existente.
     */
    private void bloquearBotones() {
        cmdNuevo.setEnabled(false);
        cmdEditar.setEnabled(false);
        cmdEliminar.setEnabled(false);
        cmdGuardar.setEnabled(true);
        cmdCancelar.setEnabled(true);
        cmdBuscar.setEnabled(false);
        cmdPrimero.setEnabled(false);
        cmdUltimo.setEnabled(false);
        cmdSiguiente.setEnabled(false);
        cmdAnterior.setEnabled(false);
    }

    /**
     * Desbloquea la utilización de los botones bloquedos durante la creación de
     * un nuevo registro o su edición.
     */
    private void desbloquearBotones() {
        cmdNuevo.setEnabled(true);
        if (listaClientes.size() > 0) {
            //En el caso que se encuentre con elementos la tabla , 
            //activa el boton Editar y Eliminar puesto que existe elemento a editar
            cmdEditar.setEnabled(true);
            cmdEliminar.setEnabled(true);
        } else { //En el caso que se encuentre vacia la tabla , 
            //desactiva el boton Editar y Eliminar puesto que no existe elemento a editar
            cmdEditar.setEnabled(false);
            cmdEliminar.setEnabled(false);
        }
        cmdGuardar.setEnabled(false);
        cmdCancelar.setEnabled(false);
        cmdBuscar.setEnabled(true);
        cmdPrimero.setEnabled(true);
        cmdUltimo.setEnabled(true);
        cmdSiguiente.setEnabled(true);
        cmdAnterior.setEnabled(true);
        
        bloquearCampos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lblDireccion = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblCI = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtCI = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtLegajo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodEmpresa = new javax.swing.JTextField();
        txtCodDepartamento = new javax.swing.JTextField();
        txtNomEmpresa = new javax.swing.JTextField();
        txtNomDepartamento = new javax.swing.JTextField();
        cmdBuscarEmprea = new javax.swing.JButton();
        cmdBuscarDepartamento = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tbrHerramientas = new javax.swing.JToolBar();
        cmdNuevo = new javax.swing.JButton();
        cmdEditar = new javax.swing.JButton();
        cmdGuardar = new javax.swing.JButton();
        cmdCancelar = new javax.swing.JButton();
        cmdEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        cmdBuscar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        panNavegacion = new javax.swing.JPanel();
        cmdPrimero = new javax.swing.JButton();
        cmdAnterior = new javax.swing.JButton();
        cmdSiguiente = new javax.swing.JButton();
        cmdUltimo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Clientes");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDireccion.setText("Dirección:");

        lblCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCliente.setText("Cliente:");

        lblCI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCI.setText("C.I.:");

        txtCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCI.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setText("Legajo:");

        txtLegajo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtLegajo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtLegajo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLegajoKeyTyped(evt);
            }
        });

        jLabel2.setText("Teléfono:");

        jLabel3.setText("Empresa:");

        jLabel4.setText("Departamento:");

        txtCodEmpresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodEmpresaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodEmpresaKeyTyped(evt);
            }
        });

        txtCodDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodDepartamentoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodDepartamentoKeyTyped(evt);
            }
        });

        txtNomEmpresa.setEditable(false);
        txtNomEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        txtNomEmpresa.setFocusable(false);

        txtNomDepartamento.setEditable(false);
        txtNomDepartamento.setBackground(new java.awt.Color(255, 255, 255));
        txtNomDepartamento.setFocusable(false);

        cmdBuscarEmprea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/buscar_16.png"))); // NOI18N
        cmdBuscarEmprea.setToolTipText("Buscar Empresa");
        cmdBuscarEmprea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarEmpreaActionPerformed(evt);
            }
        });

        cmdBuscarDepartamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/buscar_16.png"))); // NOI18N
        cmdBuscarDepartamento.setToolTipText("Buscar Departamento");
        cmdBuscarDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarDepartamentoActionPerformed(evt);
            }
        });

        jLabel5.setText("*");

        jLabel6.setText("*");

        jLabel7.setText("*");

        jLabel8.setText("*");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCI, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(txtCodEmpresa))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNomEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txtNomDepartamento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmdBuscarEmprea, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmdBuscarDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtDireccion)
                                        .addGap(11, 11, 11))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCI, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5)))
                                        .addContainerGap(57, Short.MAX_VALUE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCI, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtCodEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cmdBuscarEmprea))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCodDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNomDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmdBuscarDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)))
                .addContainerGap())
        );

        tbrHerramientas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbrHerramientas.setFloatable(false);
        tbrHerramientas.setRollover(true);

        cmdNuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/nuevo_24.png"))); // NOI18N
        cmdNuevo.setText("Nuevo");
        cmdNuevo.setFocusable(false);
        cmdNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNuevoActionPerformed(evt);
            }
        });
        tbrHerramientas.add(cmdNuevo);

        cmdEditar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/editar_24.png"))); // NOI18N
        cmdEditar.setText("Editar");
        cmdEditar.setFocusable(false);
        cmdEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditarActionPerformed(evt);
            }
        });
        tbrHerramientas.add(cmdEditar);

        cmdGuardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/guardar_24.png"))); // NOI18N
        cmdGuardar.setText("Guardar");
        cmdGuardar.setEnabled(false);
        cmdGuardar.setFocusable(false);
        cmdGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGuardarActionPerformed(evt);
            }
        });
        tbrHerramientas.add(cmdGuardar);

        cmdCancelar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/cancelar_24.png"))); // NOI18N
        cmdCancelar.setText("Cancelar");
        cmdCancelar.setEnabled(false);
        cmdCancelar.setFocusable(false);
        cmdCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelarActionPerformed(evt);
            }
        });
        tbrHerramientas.add(cmdCancelar);

        cmdEliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/borrar_24.png"))); // NOI18N
        cmdEliminar.setText("Eliminar");
        cmdEliminar.setFocusable(false);
        cmdEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEliminarActionPerformed(evt);
            }
        });
        tbrHerramientas.add(cmdEliminar);
        tbrHerramientas.add(jSeparator1);

        cmdBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmdBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/buscar_24.png"))); // NOI18N
        cmdBuscar.setText("Buscar");
        cmdBuscar.setFocusable(false);
        cmdBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cmdBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cmdBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarActionPerformed(evt);
            }
        });
        tbrHerramientas.add(cmdBuscar);
        tbrHerramientas.add(jSeparator3);
        tbrHerramientas.add(jSeparator2);

        panNavegacion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmdPrimero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/primero_16.png"))); // NOI18N
        cmdPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrimeroActionPerformed(evt);
            }
        });

        cmdAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/anterior_16.png"))); // NOI18N
        cmdAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAnteriorActionPerformed(evt);
            }
        });

        cmdSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/siguiente_16.png"))); // NOI18N
        cmdSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSiguienteActionPerformed(evt);
            }
        });

        cmdUltimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/ultimo_16.png"))); // NOI18N
        cmdUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUltimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panNavegacionLayout = new javax.swing.GroupLayout(panNavegacion);
        panNavegacion.setLayout(panNavegacionLayout);
        panNavegacionLayout.setHorizontalGroup(
            panNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panNavegacionLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(cmdPrimero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSiguiente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdUltimo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panNavegacionLayout.setVerticalGroup(
            panNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdPrimero)
            .addComponent(cmdAnterior)
            .addComponent(cmdSiguiente)
            .addComponent(cmdUltimo)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panNavegacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbrHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbrHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_cmdNuevoActionPerformed

    public void nuevo() {
        inicializarComponentes();
        desbloquearCampos();
        this.txtLegajo.grabFocus();
        estado = Estados.NUEVO;
        bloquearBotones();
    }

    private void cmdEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditarActionPerformed
        editar();
    }//GEN-LAST:event_cmdEditarActionPerformed

    public void editar() {
        if (listaClientes != null) {
            desbloquearCampos();
            this.txtCliente.grabFocus();
            estado = Estados.EDITAR;
            bloquearBotones();
        }
    }

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
       if(verificarCampos()){
           guardar();
       } else {
           JOptionPane.showMessageDialog(this, "Verifique que los campos obligatorios no se encuentren vacíos.",
                            "Cliente", JOptionPane.ERROR_MESSAGE);
           this.txtLegajo.grabFocus();
       }
    }//GEN-LAST:event_cmdGuardarActionPerformed

    public void guardar() {

        Cliente cl = new Cliente();

        try {

            Integer legajo = Integer.parseInt(this.txtLegajo.getText().trim());

            // registro nuevo
            if (estado.equals(Estados.NUEVO)) {
                Cliente aux = service.buscarPorLegajo(legajo);

                if (aux != null) {
                    JOptionPane.showMessageDialog(this, "El legajo ingresado ya se encuentra asignado a "
                            + aux.getNombre() + "." + "\n" + "Verique el legajo ingresado.",
                            "Cliente", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // edicion de registro
            //cl.setLegajo(legajo);
            cl.setNombre(this.txtCliente.getText());
            cl.setCi(this.txtCI.getText());
            cl.setDireccion(this.txtDireccion.getText());
            cl.setTelefono(this.txtTelefono.getText());
            //cl.setEmpresa(empresa);
            //cl.setDepartamento(departamento);
            cl.setUsuarioMod(usuario.getUsername());
            cl.setFechaMod(new Date());

            service.guardar(cl);
            cargarListaRegistros(0);
            desbloquearBotones();
            cmdUltimo.doClick();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error al guardar el registro. ",
                    "Cliente", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FrmClientes.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_cmdCancelarActionPerformed

    public void cancelar() {
        if (posLista > 0) {
            cliente = listaClientes.get(posLista);
            cargarCampos();
        }
        desbloquearBotones();
    }

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_cmdEliminarActionPerformed

    public void eliminar(){
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Eliminación de Registro",
                "Desea eliminar este registro.", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == 0) {
            try {
                cargarListaRegistros(0);
                desplegarPrimerRegistro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al eliminar el registro. " + ex.getMessage(),
                        "Clientes", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void cmdBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActionPerformed
        buscadorGenerico();
    }//GEN-LAST:event_cmdBuscarActionPerformed

    public void buscadorGenerico(){
        //FrmBuscar busca = new FrmBuscar(this, em, "cliente");
        //busca.setVisible(true);
    }
    
    private void cmdPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrimeroActionPerformed
        if (cantClientes > 0) {
            cliente = listaClientes.get(0);
            cargarCampos();
            posLista = 0;
        }
    }//GEN-LAST:event_cmdPrimeroActionPerformed

    private void cmdAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnteriorActionPerformed
        if (posLista > 0) {
            cliente = listaClientes.get(posLista - 1);
            cargarCampos();
            posLista--;
        }
    }//GEN-LAST:event_cmdAnteriorActionPerformed

    private void cmdSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSiguienteActionPerformed
        if (posLista < cantClientes - 1) {
            cliente = listaClientes.get(posLista + 1);
            cargarCampos();
            posLista++;
        }
    }//GEN-LAST:event_cmdSiguienteActionPerformed

    private void cmdUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUltimoActionPerformed
        if (cantClientes > 0) {
            cliente = listaClientes.get(cantClientes - 1);
            cargarCampos();
            posLista = cantClientes - 1;
        }
    }//GEN-LAST:event_cmdUltimoActionPerformed

    private void txtCodEmpresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodEmpresaKeyPressed
        
    }//GEN-LAST:event_txtCodEmpresaKeyPressed

    private void txtCodDepartamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodDepartamentoKeyPressed
       
    }//GEN-LAST:event_txtCodDepartamentoKeyPressed

    private void txtCodEmpresaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodEmpresaKeyTyped
        char car = evt.getKeyChar();

        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodEmpresaKeyTyped

    private void txtCodDepartamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodDepartamentoKeyTyped
        char car = evt.getKeyChar();

        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodDepartamentoKeyTyped

    private void txtLegajoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLegajoKeyTyped
        char car = evt.getKeyChar();

        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtLegajoKeyTyped

    private void cmdBuscarEmpreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarEmpreaActionPerformed
        
    }//GEN-LAST:event_cmdBuscarEmpreaActionPerformed

    private void cmdBuscarDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarDepartamentoActionPerformed
        
    }//GEN-LAST:event_cmdBuscarDepartamentoActionPerformed

    private Boolean verificarCampos(){
        if(this.txtLegajo.getText().trim().isEmpty()){ 
            return false;
        }
        
        if(this.txtCliente.getText().trim().isEmpty()){ 
            return false;
        }
        
        if(this.txtNomDepartamento.getText().trim().isEmpty()){ 
            return false;
        }
        
        if(this.txtNomEmpresa.getText().trim().isEmpty()){ 
            return false;
        }
        
        return true;
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAnterior;
    private javax.swing.JButton cmdBuscar;
    private javax.swing.JButton cmdBuscarDepartamento;
    private javax.swing.JButton cmdBuscarEmprea;
    private javax.swing.JButton cmdCancelar;
    private javax.swing.JButton cmdEditar;
    private javax.swing.JButton cmdEliminar;
    private javax.swing.JButton cmdGuardar;
    private javax.swing.JButton cmdNuevo;
    private javax.swing.JButton cmdPrimero;
    private javax.swing.JButton cmdSiguiente;
    private javax.swing.JButton cmdUltimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JLabel lblCI;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JPanel panNavegacion;
    private javax.swing.JToolBar tbrHerramientas;
    private javax.swing.JTextField txtCI;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodDepartamento;
    private javax.swing.JTextField txtCodEmpresa;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtLegajo;
    private javax.swing.JTextField txtNomDepartamento;
    private javax.swing.JTextField txtNomEmpresa;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
