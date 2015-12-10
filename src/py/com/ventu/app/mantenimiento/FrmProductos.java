package py.com.ventu.app.mantenimiento;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;

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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import py.com.ventu.controladores.ProductosJpaController;
import py.com.ventu.controladores.TipoProductoJpaController;
import py.com.ventu.entidades.Estados;
import py.com.ventu.entidades.Producto;
import py.com.ventu.entidades.TipoProducto;
import py.com.ventu.entidades.Usuario;

/**
 * @author cpatino
 */
public class FrmProductos extends javax.swing.JInternalFrame {

    private EntityManager em;
    private ProductosJpaController service;
    private TipoProductoJpaController tipoProductosService;

    private Producto productos = new Producto();
    private TipoProducto tipoProducto;
    
    private List<Producto> listaProductos;
    //private List<TipoProducto> listaTipoProductos;
   // private List<GrupoProducto> listaGrupoProductos;
    
    private int cantProductos;
    private int posLista;

    private Estados estado;
    private Usuario usuario;
    private JDesktopPane escritorio;

    /**
     * Crea un nuevo Formulario FrmProductos.
     */
    public FrmProductos(EntityManager em, JDesktopPane dsk, Usuario user) {
        initComponents();

        this.em = em;
        usuario = user;
        escritorio = dsk;

        service = new ProductosJpaController(em);
        tipoProductosService = new TipoProductoJpaController(em);
        
        estado = Estados.CANCELADO;
        
        keyListener();
        iniciarSpinner();

        cargarListaRegistros(0);
        desplegarPrimerRegistro();

        this.txtCodigo.grabFocus();
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
    
    private void iniciarSpinner(){
        SpinnerNumberModel model = new SpinnerNumberModel( 
            new Integer(1), // Dato visualizado al inicio en el spinner 
            new Integer(-1), // Límite inferior 
            new Integer(1), // Límite superior 
            new Integer(2) // incremento-decremento 
        ); 

       spnFactor = new JSpinner(model); 
    }

    /**
     * Carga los Campos en base al valor del objeto "Productos".
     */
    private void cargarCampos() {
        try {
            //carga de datos en los campos
            txtCodigo.setText(productos.getCodigo().toString());
            txtDescripcion.setText(productos.getDescripcion());
            txtCosto.setValue(productos.getCosto());
            txtPrecio.setValue(productos.getPrecioVenta());
            spnFactor.setValue(productos.getFactor());
            txtCodFamilia.setText(productos.getTipoProducto().getId().toString());
            txtNomFamilia.setText(productos.getTipoProducto().getDescripcion().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        bloquearCampos();
    }

    /**
     * Inicializa los Campos para ser cargados
     *
     */
    public void inicializarComponentes() {
        productos = new Producto();
        tipoProducto = new TipoProducto();
        //grupoProducto = new GrupoProducto();

        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtCosto.setValue(0);
        txtPrecio.setValue(0);
        spnFactor.setValue(1);
        txtCodFamilia.setText("");
        txtNomFamilia.setText("");
        txtCodGrupo.setText("");
        txtNomGrupo.setText("");

        desbloquearCampos();
    }

    /**
     * Recupera la lista de registros de la base de datos.
     *
     */
    public void cargarListaRegistros(int busco) {
        listaProductos = service.listarProductos();
        cantProductos = listaProductos.size();

        if (listaProductos.size() > 0) {
            posLista = busco;

            productos = listaProductos.get(posLista);

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
        if (listaProductos.size() > 0) {
            posLista = 0;
            productos = listaProductos.get(posLista);
            cargarCampos();
        }
    }

    /**
     * Desbloquea los campos para ser editados.
     */
    private void desbloquearCampos() {
        txtCodigo.setEditable(true);
        txtDescripcion.setEditable(true);
        txtCosto.setEditable(true);
        txtPrecio.setEditable(true);
        txtCodFamilia.setEditable(true);
        txtCodGrupo.setEditable(true);
    }
    
    private void bloquearCampos(){
        txtCodigo.setEditable(false);
        txtDescripcion.setEditable(false);
        txtCosto.setEditable(false);
        txtPrecio.setEditable(false);
        txtCodFamilia.setEditable(false);
        txtCodGrupo.setEditable(false);
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
        if (listaProductos.size() > 0) {
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

        btnGroupFraccionable = new javax.swing.ButtonGroup();
        btnGroupEstado = new javax.swing.ButtonGroup();
        tbrHerramientas = new javax.swing.JToolBar();
        cmdNuevo = new javax.swing.JButton();
        cmdEditar = new javax.swing.JButton();
        cmdGuardar = new javax.swing.JButton();
        cmdCancelar = new javax.swing.JButton();
        cmdEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        cmdBuscar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtCosto = new javax.swing.JFormattedTextField();
        txtPrecio = new javax.swing.JFormattedTextField();
        txtCodFamilia = new javax.swing.JTextField();
        txtNomFamilia = new javax.swing.JTextField();
        txtCodGrupo = new javax.swing.JTextField();
        txtNomGrupo = new javax.swing.JTextField();
        cmdBuscarTipo = new javax.swing.JButton();
        cmdBuscarGrupo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        spnFactor = new javax.swing.JSpinner();
        panNavegacion = new javax.swing.JPanel();
        cmdPrimero = new javax.swing.JButton();
        cmdAnterior = new javax.swing.JButton();
        cmdSiguiente = new javax.swing.JButton();
        cmdUltimo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Productos");

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

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setMaximumSize(new java.awt.Dimension(736, 448));
        jPanel3.setMinimumSize(new java.awt.Dimension(736, 448));

        jLabel1.setText("Código:");

        jLabel2.setText("Descripción:");

        jLabel3.setText("Costo:");

        jLabel4.setText("Precio Vta.:");

        jLabel5.setText("Familia:");

        jLabel6.setText("Grupo:");

        txtCodigo.setToolTipText("Código del Producto");
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        txtDescripcion.setToolTipText("Descripción del Producto");

        txtCosto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        txtCosto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCosto.setToolTipText("Costo del Producto");
        txtCosto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCostoFocusGained(evt);
            }
        });

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecio.setToolTipText("Precio de Venta del Producto");
        txtPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPrecioFocusGained(evt);
            }
        });

        txtCodFamilia.setToolTipText("Código de Familia del Producto");
        txtCodFamilia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodFamiliaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodFamiliaKeyPressed(evt);
            }
        });

        txtNomFamilia.setEditable(false);
        txtNomFamilia.setBackground(new java.awt.Color(255, 255, 255));
        txtNomFamilia.setFocusable(false);

        txtCodGrupo.setToolTipText("Código de Grupo del Producto");
        txtCodGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodGrupoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodGrupoKeyPressed(evt);
            }
        });

        txtNomGrupo.setEditable(false);
        txtNomGrupo.setBackground(new java.awt.Color(255, 255, 255));
        txtNomGrupo.setFocusable(false);

        cmdBuscarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/buscar_16.png"))); // NOI18N
        cmdBuscarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarTipoActionPerformed(evt);
            }
        });

        cmdBuscarGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/karu/imagenes/buscar_16.png"))); // NOI18N
        cmdBuscarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBuscarGrupoActionPerformed(evt);
            }
        });

        jLabel7.setText("Factor:");

        spnFactor.setOpaque(false);
        spnFactor.setRequestFocusEnabled(false);
        spnFactor.setValue(1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnFactor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCodGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                    .addComponent(txtCodFamilia))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtNomFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(txtNomGrupo)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmdBuscarGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(cmdBuscarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(spnFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCodFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdBuscarTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdBuscarGrupo))
                .addContainerGap())
        );

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
                .addComponent(cmdPrimero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdAnterior)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdSiguiente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdUltimo)
                .addGap(0, 196, Short.MAX_VALUE))
        );
        panNavegacionLayout.setVerticalGroup(
            panNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdPrimero, javax.swing.GroupLayout.Alignment.TRAILING)
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
                    .addComponent(tbrHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(panNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbrHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, Short.MAX_VALUE)
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
        this.txtCodigo.grabFocus();
        estado = Estados.NUEVO;
        bloquearBotones();
    }

    private void cmdEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditarActionPerformed
        editar();
    }//GEN-LAST:event_cmdEditarActionPerformed

    public void editar() {
        desbloquearCampos();
        this.txtCodigo.grabFocus();
        estado = Estados.EDITAR;
        bloquearBotones();
    }

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_cmdGuardarActionPerformed

    public void guardar() {
        if (txtCodigo.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe insertar un valor valido en la columna Codigo. ", "Productos", JOptionPane.ERROR_MESSAGE);
        }
        try {
            productos.setCodigo(Integer.parseInt(this.txtCodigo.getText()));
            productos.setDescripcion(txtDescripcion.getText());
            productos.setCosto(Double.parseDouble(txtCosto.getValue().toString()));
            productos.setPrecioVenta(Double.parseDouble(txtPrecio.getValue().toString()));
            productos.setFactor(Integer.parseInt(spnFactor.getValue().toString()));
            productos.setTipoProducto(tipoProducto);
            productos.setFechaMod(new Date());
            productos.setUsuarioMod(usuario.getUsername());

            service.guardar(productos);
  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al guardar el registro. " + "\n"
                    + e.getMessage(), "Productos", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Inserta el producto en la tabla existenciaStock.
       /* try {
            if (estado.equals(Estados.NUEVO)) {
                //List<Sucursal> listaSucursales = sucursalesService.listarSucursales();
                //productos = service.buscarProductos(service.valorActual().intValue());

               // for (Sucursal sucur : listaSucursales) {
                    ExistenciaStock existenciaSucursal = new ExistenciaStock(1, productos.getCodigo());
                    existenciaSucursal.setStockActual(0.0);
                    existenciaSucursal.setStockMinimo(0.0);
                    existenciaSucursal.setFechaMod(new Date());
                    existenciaSucursal.setUsuarioMod(usuario.getUsername());
                    existenciaService.guardar(existenciaSucursal);
                //}
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Se ha producido una error al guardar el registro.",
                    "Error.", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FrmProductos.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        estado = Estados.CANCELADO;
        cargarListaRegistros(0);
        desbloquearBotones();
        cmdUltimo.doClick();
    }

    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_cmdCancelarActionPerformed

    public void cancelar() {
        if (posLista > 0) {
            productos = listaProductos.get(posLista);
            cargarCampos();
        }
        desbloquearBotones();
    }

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_cmdEliminarActionPerformed

    public void eliminar() {
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Eliminación de Registro",
                "Desea eliminar este registro.", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == 0) {
            try {
                service.borrar(productos);
                cargarListaRegistros(0);
                desplegarPrimerRegistro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al eliminar el registro. " + ex.getMessage(),
                        "Productos", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void cmdBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_cmdBuscarActionPerformed

    public void buscar() {
       // FrmBuscar busco = new FrmBuscar(this, em, "productos");
        //busco.setVisible(true);
    }

    private void cmdPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrimeroActionPerformed
        if (cantProductos > 0) {
            productos = listaProductos.get(0);
            cargarCampos();
            posLista = 0;
        }
    }//GEN-LAST:event_cmdPrimeroActionPerformed

    private void cmdAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnteriorActionPerformed
        if (posLista > 0) {
            productos = listaProductos.get(posLista - 1);
            cargarCampos();
            posLista--;
        }
    }//GEN-LAST:event_cmdAnteriorActionPerformed

    private void cmdSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSiguienteActionPerformed
        if (posLista < cantProductos - 1) {
            productos = listaProductos.get(posLista + 1);
            cargarCampos();
            posLista++;
        }
    }//GEN-LAST:event_cmdSiguienteActionPerformed

    private void cmdUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUltimoActionPerformed
        if (cantProductos > 0) {
            productos = listaProductos.get(cantProductos - 1);
            cargarCampos();
            posLista = cantProductos - 1;
        }
    }//GEN-LAST:event_cmdUltimoActionPerformed

    private void txtCodFamiliaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodFamiliaKeyPressed
        if (!txtCodFamilia.getText().trim().isEmpty() || txtCodFamilia.getText() != null) {
            if (evt.getKeyCode() == VK_ENTER || evt.getKeyCode() == evt.VK_TAB) {
                buscarTipoProducto();
            }
        }
    }//GEN-LAST:event_txtCodFamiliaKeyPressed

    private void txtCodGrupoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodGrupoKeyPressed
        if (!txtCodFamilia.getText().trim().isEmpty() ||  this.txtCodFamilia.getText() != null) {
            if (evt.getKeyCode() == VK_ENTER || evt.getKeyCode() == evt.VK_TAB) {
                buscarGrupoProducto();
            }
        }
    }//GEN-LAST:event_txtCodGrupoKeyPressed

    private void txtCodFamiliaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodFamiliaKeyTyped
        char car = evt.getKeyChar();

        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodFamiliaKeyTyped

    private void txtCodGrupoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodGrupoKeyTyped
        char car = evt.getKeyChar();

        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodGrupoKeyTyped

    private void cmdBuscarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarTipoActionPerformed
       // FrmBuscar busca = new FrmBuscar(this, em, "productoTipoProducto");
        //busca.setVisible(true);
    }//GEN-LAST:event_cmdBuscarTipoActionPerformed

    private void cmdBuscarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarGrupoActionPerformed
       // FrmBuscar busca = new FrmBuscar(this, em, "productoGrupoProducto");
       // busca.setVisible(true);
    }//GEN-LAST:event_cmdBuscarGrupoActionPerformed

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        char car = evt.getKeyChar();

        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtCostoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCostoFocusGained
       this.txtCosto.selectAll();
    }//GEN-LAST:event_txtCostoFocusGained

    private void txtPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioFocusGained
        this.txtPrecio.selectAll();
    }//GEN-LAST:event_txtPrecioFocusGained

    private void buscarGrupoProducto(){
        //try{
           // GrupoProducto auxGrupo = new GrupoProducto();
            Integer idGrupo = Integer.parseInt(this.txtCodGrupo.getText());
            
           // auxGrupo = grupoProductosService.buscarGrupoProducto(idGrupo);
            
            //if(auxGrupo != null){
              //  grupoProducto = auxGrupo;
               // this.txtNomGrupo.setText(grupoProducto.getDescripcion());
          // } else {
          //      this.txtNomGrupo.setText("");
         //       JOptionPane.showMessageDialog(null, "No se encontró registro asociado con el código ingresado.",
                  //  "Productos", JOptionPane.ERROR_MESSAGE);
           // }
       // } catch(Exception e){
          //  e.printStackTrace();
       // }
    }
    
    private void buscarTipoProducto(){
        try{
            TipoProducto auxTipo = new TipoProducto();
            Integer idTipo = Integer.parseInt(this.txtCodFamilia.getText());
            
            auxTipo = tipoProductosService.buscarTipoProductos(idTipo);
            
            if(auxTipo != null){
                tipoProducto = auxTipo;
                this.txtNomFamilia.setText(tipoProducto.getDescripcion());
            } else {
                this.txtNomFamilia.setText("");
                JOptionPane.showMessageDialog(null, "No se encontró registro asociado con el código ingresado.",
                    "Productos", JOptionPane.ERROR_MESSAGE);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void seleccionarTipoProducto(int id) {
        try {
            TipoProducto auxTp = tipoProductosService.buscarTipoProductos(id);

            if (auxTp != null) {
                tipoProducto = auxTp;
                txtCodFamilia.setText(String.valueOf(auxTp.getId()));
                txtNomFamilia.setText(auxTp.getDescripcion());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupEstado;
    private javax.swing.ButtonGroup btnGroupFraccionable;
    private javax.swing.JButton cmdAnterior;
    private javax.swing.JButton cmdBuscar;
    private javax.swing.JButton cmdBuscarGrupo;
    private javax.swing.JButton cmdBuscarTipo;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPanel panNavegacion;
    private javax.swing.JSpinner spnFactor;
    private javax.swing.JToolBar tbrHerramientas;
    private javax.swing.JTextField txtCodFamilia;
    private javax.swing.JTextField txtCodGrupo;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JFormattedTextField txtCosto;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNomFamilia;
    private javax.swing.JTextField txtNomGrupo;
    private javax.swing.JFormattedTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
