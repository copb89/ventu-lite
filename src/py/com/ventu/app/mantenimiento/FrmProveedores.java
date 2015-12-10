package py.com.ventu.app.mantenimiento;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_F12;
import static java.awt.event.KeyEvent.VK_F2;
import static java.awt.event.KeyEvent.VK_F3;
import static java.awt.event.KeyEvent.VK_F5;
import static java.awt.event.KeyEvent.VK_F9;
import java.awt.event.KeyListener;
//import py.com.ventu.app.util.buscadores.FrmBuscar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import py.com.ventu.controladores.ProveedoresJpaController;
import py.com.ventu.controladores.exceptions.NonexistentEntityException;
import py.com.ventu.entidades.Proveedor;
import py.com.ventu.entidades.Usuario;

/**
 * @author Alcides
 */
public class FrmProveedores extends javax.swing.JInternalFrame {

    private EntityManager em;
    private ProveedoresJpaController service;
    // Objeto de Entidad Proveedores que sera usada en todo el formulario
    //si es necesario se instancia de vuelta para setear en null los valores.
    private Proveedor proveedores = new Proveedor();
    private List<Proveedor> listaProveedores;
    private Usuario usuario;

    private JDesktopPane escritorio;

    private int cantProveedores;
    private int posLista;

    /**
     * Creates new form FrmGrupoCliente
     */
    public FrmProveedores(EntityManager em, JDesktopPane dsk, Usuario user) {
        initComponents();

        this.em = em;
        this.usuario = user;
        this.escritorio = dsk;

        service = new ProveedoresJpaController(em);

        keyListener();
        cargarListaRegistros(0);
        desplegarPrimerRegistro();

        this.txtProveedor.grabFocus();
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
            buscar();
        }

        // F12 - imprimir ingreso
        if (evt.getKeyCode() == VK_F12) {

        }

        // ESC - cancelar operacion
        if (evt.getKeyCode() == VK_ESCAPE) {
            cancelar();
        }
    }

    /**
     * Carga los Campos en base al valor del objeto "Proveedores".
     *
     */
    private void cargarCampos() {
        txtCodigo.setText(proveedores.getId().toString());
        txtProveedor.setText(proveedores.getNombre());
        txtDireccion.setText(proveedores.getDireccion());
        txtRuc.setText(proveedores.getRuc());
        txtTelefono.setText(proveedores.getTelefono());
        txtEmail.setText(proveedores.getEmail());

        // bloquar Campos        
        txtProveedor.setEditable(false);
        txtDireccion.setEditable(false);
        txtRuc.setEditable(false);
        txtTelefono.setEditable(false);
        txtEmail.setEditable(false);
        txtFax.setEditable(false);
        txtContacto1.setEditable(false);
        txtTelefono1.setEditable(false);
        txtContacto2.setEditable(false);
        txtTelefono2.setEditable(false);
    }

    /**
     * Inicializa los Campos para ser cargados
     *
     */
    public void inicializarComponentes() {
        proveedores = new Proveedor();
        this.txtCodigo.setText("");
        this.txtProveedor.setText("");
        this.txtDireccion.setText("");
        this.txtRuc.setText("");
        this.txtTelefono.setText("");
        this.txtEmail.setText("");
        this.txtFax.setText("");
        this.txtContacto1.setText("");
        this.txtTelefono1.setText("");
        this.txtContacto2.setText("");
        this.txtTelefono2.setText("");
        desbloquearCampos();
    }

    /**
     * Recupera la lista de registros de la base de datos.
     *
     */
    public void cargarListaRegistros(int busco) {
        listaProveedores = service.listarProveedores();
        cantProveedores = listaProveedores.size();

        if (listaProveedores.size() > 0) {
            posLista = busco;

            proveedores = listaProveedores.get(posLista);

            txtCodigo.setText(proveedores.getId().toString());
            txtProveedor.setText(proveedores.getNombre());
            txtDireccion.setText(proveedores.getDireccion());
            txtRuc.setText(proveedores.getRuc());
            txtTelefono.setText(proveedores.getTelefono());
            txtEmail.setText(proveedores.getEmail());
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
     *
     */
    private void desplegarPrimerRegistro() {
        if (listaProveedores.size() > 0) {
            posLista = 0;

            proveedores = listaProveedores.get(posLista);

            txtCodigo.setText(proveedores.getId().toString());
            txtProveedor.setText(proveedores.getNombre());
            txtDireccion.setText(proveedores.getDireccion());
            txtRuc.setText(proveedores.getRuc());
            txtTelefono.setText(proveedores.getTelefono());
            txtEmail.setText(proveedores.getEmail());

        }
    }

    /**
     * Desbloquea los campos para ser editados.
     *
     */
    private void desbloquearCampos() {
        txtProveedor.setEditable(true);
        txtDireccion.setEditable(true);
        txtRuc.setEditable(true);
        txtTelefono.setEditable(true);
        txtEmail.setEditable(true);
        txtFax.setEditable(true);
        txtContacto1.setEditable(true);
        txtTelefono1.setEditable(true);
        txtContacto2.setEditable(true);
        txtTelefono2.setEditable(true);
    }

    /**
     * Bloquea la utilización de los botones innecesarios durante la carga de un
     * nuevo registro o la edición de uno existente.
     *
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
     *
     */
    private void DesbloquearBotones() {
        cmdNuevo.setEnabled(true);
        if (listaProveedores.size() > 0) {
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txtProveedor = new javax.swing.JTextField();
        lblProveedor = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblRuc = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblFax = new javax.swing.JLabel();
        txtFax = new javax.swing.JTextField();
        lblContacto1 = new javax.swing.JLabel();
        txtContacto1 = new javax.swing.JTextField();
        lblTelefono1 = new javax.swing.JLabel();
        txtTelefono1 = new javax.swing.JTextField();
        lblContacto2 = new javax.swing.JLabel();
        txtContacto2 = new javax.swing.JTextField();
        lblTelefono2 = new javax.swing.JLabel();
        txtTelefono2 = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        tbrHerramientas = new javax.swing.JToolBar();
        cmdNuevo = new javax.swing.JButton();
        cmdEditar = new javax.swing.JButton();
        cmdGuardar = new javax.swing.JButton();
        cmdCancelar = new javax.swing.JButton();
        cmdEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        cmdBuscar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        panNavegacion = new javax.swing.JPanel();
        cmdPrimero = new javax.swing.JButton();
        cmdAnterior = new javax.swing.JButton();
        cmdSiguiente = new javax.swing.JButton();
        cmdUltimo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Proveedores");
        setMinimumSize(new java.awt.Dimension(682, 391));
        setPreferredSize(new java.awt.Dimension(682, 391));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedorActionPerformed(evt);
            }
        });
        txtProveedor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProveedorFocusLost(evt);
            }
        });
        txtProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProveedorKeyPressed(evt);
            }
        });

        lblProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblProveedor.setText("Proveedor:");

        lblDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDireccion.setText("Dirección:");

        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
        });

        lblRuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRuc.setText("Ruc:");

        txtRuc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRucKeyPressed(evt);
            }
        });

        lblTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTelefono.setText("Teléfono:");

        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
        });

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblEmail.setText("E-mail:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblFax.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFax.setText("Fax:");

        txtFax.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFax.setToolTipText("");
        txtFax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFaxKeyPressed(evt);
            }
        });

        lblContacto1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblContacto1.setText("Contacto 1:");

        txtContacto1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTelefono1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTelefono1.setText("Teléfono 1:");

        txtTelefono1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblContacto2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblContacto2.setText("Contacto 2:");

        txtContacto2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtContacto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContacto2ActionPerformed(evt);
            }
        });

        lblTelefono2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTelefono2.setText("Teléfono 2:");

        txtTelefono2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(java.awt.Color.lightGray);
        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(lblContacto2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtContacto2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTelefono2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblTelefono)
                                .addComponent(lblRuc)
                                .addComponent(lblContacto1)
                                .addComponent(lblProveedor)
                                .addComponent(lblDireccion))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(txtProveedor))
                                        .addGap(1, 1, 1)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblFax)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFax, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                    .addComponent(txtDireccion)
                                    .addComponent(txtEmail))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtContacto1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTelefono1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(112, 112, 112))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProveedor)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRuc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFax)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContacto1)
                    .addComponent(txtContacto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefono1)
                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContacto2)
                    .addComponent(txtContacto2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefono2)
                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addComponent(tbrHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbrHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtContacto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContacto2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContacto2ActionPerformed

    private void cmdNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_cmdNuevoActionPerformed

    public void nuevo() {
        inicializarComponentes();
        desbloquearCampos();

        this.txtProveedor.grabFocus();

        bloquearBotones();
    }

    private void cmdEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditarActionPerformed
        editar();
    }//GEN-LAST:event_cmdEditarActionPerformed

    public void editar() {
        if (listaProveedores != null) {
            desbloquearCampos();
            this.txtProveedor.grabFocus();
            bloquearBotones();
        }
    }

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_cmdGuardarActionPerformed

    public void guardar() {
        proveedores.setNombre(this.txtProveedor.getText().trim());
        proveedores.setDireccion(this.txtDireccion.getText().trim());
        proveedores.setRuc(this.txtRuc.getText().trim());
        proveedores.setTelefono(this.txtTelefono.getText().trim());
        proveedores.setEmail(this.txtEmail.getText().trim());
        proveedores.setFechaMod(new Date());

        try {
            service.guardar(proveedores);
            cargarListaRegistros(0);
            DesbloquearBotones();
            cmdUltimo.doClick();
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(null, "El registro de Proveedores no existe en la base de datos. ",
                    "Error.", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FrmProveedores.class.getName()).log(Level.SEVERE, "El registro de Proveedores no existe en la base de datos. ", ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Se ha producido una Excepción. Consulte el archivo log. ",
                    "Error.", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FrmProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_cmdCancelarActionPerformed

    public void cancelar() {
        if (posLista > 0) {
            proveedores = listaProveedores.get(posLista);
            cargarCampos();
        }
        DesbloquearBotones();
    }

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_cmdEliminarActionPerformed

    public void eliminar() {
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Eliminación de Registro",
                "Desea eliminar este registro.", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == 0) {
            try {
                service.borrar(proveedores);
                cargarListaRegistros(0);
                desplegarPrimerRegistro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al eliminar el registro. " + ex.getMessage(),
                        "Proveedores", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cmdBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_cmdBuscarActionPerformed

    public void buscar() {
        //FrmBuscar busco = new FrmBuscar(this, em, "proveedores");
        //busco.setVisible(true);
    }

    private void cmdPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrimeroActionPerformed
        if (cantProveedores > 0) {
            proveedores = listaProveedores.get(0);
            cargarCampos();
            posLista = 0;
        }
    }//GEN-LAST:event_cmdPrimeroActionPerformed

    private void cmdAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnteriorActionPerformed
        if (posLista > 0) {
            proveedores = listaProveedores.get(posLista - 1);
            cargarCampos();
            posLista--;
        }
    }//GEN-LAST:event_cmdAnteriorActionPerformed

    private void cmdSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSiguienteActionPerformed
        if (posLista < cantProveedores - 1) {
            proveedores = listaProveedores.get(posLista + 1);
            cargarCampos();
            posLista++;
        }
    }//GEN-LAST:event_cmdSiguienteActionPerformed

    private void cmdUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUltimoActionPerformed
        if (cantProveedores > 0) {
            proveedores = listaProveedores.get(cantProveedores - 1);
            cargarCampos();
            posLista = cantProveedores - 1;
        }
    }//GEN-LAST:event_cmdUltimoActionPerformed

    private void txtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtProveedorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProveedorFocusLost

    }//GEN-LAST:event_txtProveedorFocusLost

    private void txtProveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProveedorKeyPressed
        procesarKeyEvent(evt);
    }//GEN-LAST:event_txtProveedorKeyPressed

    private void txtRucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyPressed
        procesarKeyEvent(evt);
    }//GEN-LAST:event_txtRucKeyPressed

    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        procesarKeyEvent(evt);
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        procesarKeyEvent(evt);
    }//GEN-LAST:event_txtTelefonoKeyPressed

    private void txtFaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFaxKeyPressed
        procesarKeyEvent(evt);
    }//GEN-LAST:event_txtFaxKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAnterior;
    private javax.swing.JButton cmdBuscar;
    private javax.swing.JButton cmdCancelar;
    private javax.swing.JButton cmdEditar;
    private javax.swing.JButton cmdEliminar;
    private javax.swing.JButton cmdGuardar;
    private javax.swing.JButton cmdNuevo;
    private javax.swing.JButton cmdPrimero;
    private javax.swing.JButton cmdSiguiente;
    private javax.swing.JButton cmdUltimo;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JLabel lblContacto1;
    private javax.swing.JLabel lblContacto2;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFax;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblRuc;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTelefono1;
    private javax.swing.JLabel lblTelefono2;
    private javax.swing.JPanel panNavegacion;
    private javax.swing.JToolBar tbrHerramientas;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtContacto1;
    private javax.swing.JTextField txtContacto2;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    // End of variables declaration//GEN-END:variables
}
