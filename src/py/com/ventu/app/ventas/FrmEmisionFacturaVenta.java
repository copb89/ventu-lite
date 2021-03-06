package py.com.ventu.app.ventas;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_F12;
import static java.awt.event.KeyEvent.VK_F2;
import static java.awt.event.KeyEvent.VK_F3;

import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import py.com.ventu.controladores.ConfiguracionesJpaController;
import py.com.ventu.controladores.FacturaVentaDetJpaController;
import py.com.ventu.controladores.FacturaVentaJpaController;
import py.com.ventu.entidades.Configuraciones;
import py.com.ventu.entidades.FacturaVenta;
import py.com.ventu.entidades.FacturaVentaDet;
import py.com.ventu.entidades.Usuario;
import py.com.ventu.util.NumeroToLetras;

/**
 * @author cpatino
 */
public class FrmEmisionFacturaVenta extends javax.swing.JInternalFrame {

    private EntityManager em;

    private Usuario usuario;
    private FacturaVenta facturaVenta;
    private Configuraciones conf;
    private FacturaVentaJpaController facturaVentaService;
    private FacturaVentaDetJpaController facturaVentaDetService;
    private ConfiguracionesJpaController configuracionesService;

    private Logger log;

    private DefaultTableModel modelProductos;

    private String formulario;

    private boolean habilitado = false;

    /**
     * Creates new form FrmEmisionFacturaVenta
     */
    public FrmEmisionFacturaVenta() {
        initComponents();
    }

    public FrmEmisionFacturaVenta(EntityManager ema, Usuario user, Logger logger) {
        initComponents();

        this.em = ema;
        this.usuario = user;
        this.log = logger;

        iniciarComponentes(); // en caso que se instancien con diferentes parametros

    }

    public void iniciarComponentes() {
        facturaVentaService = new FacturaVentaJpaController(em);
        configuracionesService = new ConfiguracionesJpaController(em);
        facturaVentaDetService = new FacturaVentaDetJpaController(em);

        keyListener();
        inicializarValores();
        tableModelProductos();
        inicializarComprobante();
        nuevo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNroTicket = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblFechaTicket = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTipoVenta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblGrupo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblVendedor = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabProductos = new javax.swing.JTable();
        lblTotalVenta = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        cmdNuevo = new javax.swing.JButton();
        cmdImprimir = new javax.swing.JButton();
        cmdGuardar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lblNroComprobante = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Emitir Factura Venta");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Ticket:");

        txtNroTicket.setBackground(new java.awt.Color(255, 255, 204));
        txtNroTicket.setToolTipText("Nro. de Ticket a ser buscado");
        txtNroTicket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNroTicketKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroTicketKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNroTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNroTicket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Cliente:");

        lblCliente.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblCliente.setText("Cliente");

        jLabel4.setText("Fecha:");

        lblFechaTicket.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblFechaTicket.setText("lblFechaTicket");

        jLabel6.setText("Tipo Venta:");

        lblTipoVenta.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblTipoVenta.setText("lblTipoVenta");

        jLabel3.setText("Grupo:");

        lblGrupo.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblGrupo.setText("lblGrupo");

        jLabel5.setText("Vendedor:");

        lblVendedor.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        lblVendedor.setText("lblVendedor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblCliente))
                    .addComponent(lblGrupo)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblFechaTicket))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblTipoVenta)
                    .addComponent(jLabel5)
                    .addComponent(lblVendedor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Producto", "Cant", "Precio", "Dto. %", "Dscto.", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabProductos.setRowHeight(22);
        tabProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabProductosMouseClicked(evt);
            }
        });
        tabProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabProductos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lblTotalVenta.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        lblTotalVenta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalVenta.setText("lblTotalVenta");

        jLabel7.setText("Total Venta:");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmdNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/progress/sgf/imagenes/nuevo_16.png"))); // NOI18N
        cmdNuevo.setToolTipText("Nueva venta");
        cmdNuevo.setPreferredSize(new java.awt.Dimension(40, 40));
        cmdNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNuevoActionPerformed(evt);
            }
        });

        cmdImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/progress/sgf/imagenes/imprimir_16.png"))); // NOI18N
        cmdImprimir.setToolTipText("Imprimir Comprobante de Venta");
        cmdImprimir.setPreferredSize(new java.awt.Dimension(40, 40));
        cmdImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdImprimirActionPerformed(evt);
            }
        });

        cmdGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/progress/sgf/imagenes/guardar_16.png"))); // NOI18N
        cmdGuardar.setToolTipText("Guardar");
        cmdGuardar.setPreferredSize(new java.awt.Dimension(40, 40));
        cmdGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNroComprobante.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        lblNroComprobante.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNroComprobante.setText("001-002-1234567");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Nro. Factura:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNroComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNroComprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalVenta)
                    .addComponent(jLabel7))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNroTicketKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroTicketKeyTyped
        char car = evt.getKeyChar();
        if (car >= '0' && car <= '9') {
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_txtNroTicketKeyTyped

    private void tabProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabProductosMouseClicked

    }//GEN-LAST:event_tabProductosMouseClicked

    private void tabProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabProductosKeyPressed
        procesarKeyEvent(evt);
    }//GEN-LAST:event_tabProductosKeyPressed

    private void txtNroTicketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroTicketKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            if (this.txtNroTicket.getText() != null
                    || !this.txtNroTicket.getText().trim().equals("0")) {
                buscarVenta(this.txtNroTicket.getText());
            } else {
                habilitado = false;
                JOptionPane.showMessageDialog(null, "Debe ingresar el número de ticket de venta.",
                        formulario, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            procesarKeyEvent(evt);
        }
    }//GEN-LAST:event_txtNroTicketKeyPressed

    private void cmdNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_cmdNuevoActionPerformed

    public void nuevo() {
        inicializarValores();
        tableModelProductos();
        inicializarComprobante();
        this.txtNroTicket.grabFocus();
    }

    private void cmdImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdImprimirActionPerformed
        paraImprimir();
    }//GEN-LAST:event_cmdImprimirActionPerformed

    public void paraImprimir() {
        if (habilitado) {
            imprimir();
        } else {
            JOptionPane.showMessageDialog(null, "No se puede imprimir porque no se recuperaron todos los valores necesarios para la operación."
                    + "\n" + "Verifique nro. de comprobante o ticket de venta",
                    formulario, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
        preProcesar();
    }//GEN-LAST:event_cmdGuardarActionPerformed

    public void preProcesar() {
        if (habilitado) {
            procesar();
        } else {
            JOptionPane.showMessageDialog(null, "No se puede guardar porque no se recuperaron todos los valores necesarios para la operación."
                    + "\n" + "Verifique nro. de comprobante o ticket de venta",
                    formulario, JOptionPane.INFORMATION_MESSAGE);
        }
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
            preProcesar();
        }

        // F12 - imprimir ingreso
        if (evt.getKeyCode() == VK_F12) {
            paraImprimir();
        }
    }

    private void inicializarValores() {
        this.txtNroTicket.setText("");
        this.lblNroComprobante.setText("");
        this.lblCliente.setText("");
        this.lblGrupo.setText("");
        this.lblFechaTicket.setText("");
        this.lblTipoVenta.setText("");
        this.lblVendedor.setText("");
        this.lblTotalVenta.setText("0");

        habilitado = true;

        this.formulario = "Emisión Factura Venta";

        conf = configuracionesService.buscarConfiguraciones(1);

        if (conf == null) {
            habilitado = false;
            JOptionPane.showMessageDialog(null, "No se han recuperado valores de configuración del sistema."
                    + "\n" + "Verifique que hayan sido configurados. (Configuración -> Configuración General).",
                    formulario, JOptionPane.ERROR_MESSAGE);
            log.info(formulario + "; Proceso: InicializarValores ; " + " ; CAUSA: " + "No se han recuperado valores de configuración del sistema.");
        }
    }

    public void tableModelProductos() {
        modelProductos = (DefaultTableModel) this.tabProductos.getModel();
        modelProductos.setNumRows(0);

        tabProductos.getColumnModel().getColumn(1).setPreferredWidth(230);
        tabProductos.getColumnModel().getColumn(2).setPreferredWidth(60);
        tabProductos.getColumnModel().getColumn(4).setPreferredWidth(60);
    }

    public void inicializarComprobante() {
       /* try {

            tipoComprobante = tipoComprobantesService.buscarTipoComprobantes(2);
            comprobante = comprobantesService.buscarComprobante(tipoComprobante, true, conf.getSucursal(), conf.getCaja());

            if (comprobante == null) {
                habilitado = false;
                JOptionPane.showMessageDialog(null, "No se recuperó comprobante activo o no se encuentra definido uno para esta sucursal o caja."
                        + "\n" + "Verifique en parámetros de configuración de comprobantes.",
                        formulario, JOptionPane.ERROR_MESSAGE);
            } else {
                // validad que el timbrado no se encuentre vencido
                if (comprobante.getVencTimbrado().after(new Date())) {
                    habilitado = true;
                    this.lblNroComprobante.setText(comprobante.getCodSucFac() + " - "
                            + comprobante.getCodExpFac() + " - " + comprobante.getNroComprobante());
                } else {
                    habilitado = false;
                    JOptionPane.showMessageDialog(null, "El lote de comprobantes se encuentra con Timbrado vencido."
                            + "\n" + "Agregue nuevo lote de comprobantes.",
                            formulario, JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
            habilitado = false;
            JOptionPane.showMessageDialog(null, "Error al recuperar valores del sistema."
                    + "\n" + e.getMessage() + "\n" + e.getCause(),
                    formulario, JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            log.info(formulario + "; Proceso: InicializarValores ; " + " ; CAUSA: " + "Error al inicializar valores por defecto del sistema. " + e.getCause());
        }*/
    }

    public void buscarVenta(String nroTicket) {
        try {
            Integer ticket = Integer.parseInt(nroTicket);

            //facturaVenta = facturaVentaService.buscarPorTicket(ticket, conf.getCaja(), conf.getSucursal());

            if (facturaVenta == null) {
                habilitado = false;
                JOptionPane.showMessageDialog(null, "No se encontró ninguna venta con el nro. de ticket ingresado."
                        + "\n" + "Ingrese de nuevo.", formulario, JOptionPane.INFORMATION_MESSAGE);
            } else {

               /* if (facturaVenta.getNroDocum() != 0) {
                    JOptionPane.showMessageDialog(null, "Ya fue emitida una factura de venta para este ticket.", formulario, JOptionPane.INFORMATION_MESSAGE);
                    habilitado = false;
                } else {
                    List<FacturaVentaDet> listaDetalle = facturaVentaDetService.buscarListaDetalleVenta(facturaVenta.getNroTrans());
                    habilitado = true;
                    tableModelProductos();
                    cargarDetalleVenta(facturaVenta, listaDetalle);
                }*/
            }

        } catch (Exception e) {
            habilitado = false;
            JOptionPane.showMessageDialog(null, "Ocurrió un error al recuperar la venta."
                    + "\n" + e.getMessage() + "\n" + e.getCause(),
                    formulario, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarDetalleVenta(FacturaVenta fv, List<FacturaVentaDet> listaDetalle) {
       /* this.lblCliente.setText(fv.getCliente().getId() + "  " + fv.getCliente().getCliente());
        this.lblGrupo.setText(fv.getCliente().getGrupoCliente().getDescripcion());
        this.lblFechaTicket.setText(new SimpleDateFormat("dd/MM/yyyy").format(fv.getFecha()));
        this.lblVendedor.setText(fv.getVendedor().getNombre());*/
//        this.lblTotalVenta.setText(String.valueOf(fv.getTotal()));

        if (fv.getCondicionVenta() == 1) {
            this.lblTipoVenta.setText("1 Venta Contado");
        }
        if (fv.getCondicionVenta() == 2) {
            this.lblTipoVenta.setText("2 Venta Crédito");
        } else {
            this.lblTipoVenta.setText("3 Venta Tarjeta");
        }

        cargarDetalleVenta(listaDetalle);

    }

    public void cargarDetalleVenta(List<FacturaVentaDet> listaDetalle) {
        try {
            for (FacturaVentaDet fvd : listaDetalle) {
                modelProductos.addRow(new Object[]{
                    fvd.getProducto().getCodigo(),
                    //fvd.getProducto().getDescripcion() + " " + fvd.getProducto().getPresentacion(),
                    fvd.getCantidad(),
                    fvd.getPrecioUnit(),
                    fvd.getProcDscto(),
                    fvd.getImpDscto(),
                    fvd.getSubototal()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al cargar detalle de la venta."
                    + "\n" + ex.getMessage(), formulario, JOptionPane.ERROR_MESSAGE);
            log.info(formulario + "; Proceso: CargarDetalleVenta ; " + ex.getMessage() + " ; CAUSA: " + ex.getCause());
            ex.printStackTrace();
        }
    }

    public void procesar() {
        try {
            /*facturaVenta.setCodSucFac(comprobante.getCodSucFac());
            facturaVenta.setCodEmiFac(comprobante.getCodExpFac());
            facturaVenta.setNroDocum(comprobante.getNroComprobante());

            facturaVentaService.editar(facturaVenta);
            comprobantesService.actualizarNroComprobante(comprobante);
*/
            imprimir();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar detalle de la venta."
                    + "\n" + e.getMessage(), formulario, JOptionPane.ERROR_MESSAGE);
            log.info(formulario + "; Proceso: Procesar ; " + e.getMessage() + " ; CAUSA: " + e.getCause());
            e.printStackTrace();
        }
    }

    public void imprimir() {
        try {
            Connection conexion;
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sgf", "postgres", "postgres");

            // retorna el valor del importe en letras
            NumeroToLetras imp = new NumeroToLetras();
//            String letras = imp.Convertir(this.facturaVenta.getTotal().toString(), true, 1);

            Map parametros = new HashMap();
            //parametros que enviamos al report.
            parametros.put("nroTrans", facturaVenta.getTicket());
//            parametros.put("monto_letras", letras);
            JasperReport elReporte = (JasperReport) JRLoader.loadObject(ClassLoader.getSystemResource("py/com/progress/sgf/reportes/FacturaVenta.jasper"));
            JasperPrint imprimir = JasperFillManager.fillReport(elReporte, parametros, conexion);
            JasperViewer visor = new JasperViewer(imprimir, false);
            visor.setTitle("Emitir Factura de Venta");
            visor.setVisible(true);
            conexion.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al imprimir factura de venta."
                    + "\n" + ex.getMessage() + "\n" + ex.getCause(),
                    "Emitir Factura de Venta", JOptionPane.ERROR_MESSAGE);
            log.info(formulario + "; Proceso: ImprimirRecibo ; " + ex.getMessage() + " ; CAUSA: " + ex.getCause());
            ex.printStackTrace();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdGuardar;
    private javax.swing.JButton cmdImprimir;
    private javax.swing.JButton cmdNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFechaTicket;
    private javax.swing.JLabel lblGrupo;
    private javax.swing.JLabel lblNroComprobante;
    private javax.swing.JLabel lblTipoVenta;
    private javax.swing.JLabel lblTotalVenta;
    private javax.swing.JLabel lblVendedor;
    private javax.swing.JTable tabProductos;
    private javax.swing.JTextField txtNroTicket;
    // End of variables declaration//GEN-END:variables
}
