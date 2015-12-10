package py.com.ventu.app.util.buscadores;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;
import py.com.ventu.controladores.ProveedoresJpaController;
import py.com.ventu.controladores.exceptions.NonexistentEntityException;
import py.com.ventu.entidades.Proveedor;

/**
 * @author Carlos Patino
 */
public class FrmBuscarGenerico extends javax.swing.JDialog {

    private EntityManager em;
    private DefaultTableModel model;
    private String origen;
    private String codRetorno;

    //Definicion de los servicios para cada entidad
    private ProveedoresJpaController proveedoresJpaController;

    public FrmBuscarGenerico(JInternalFrame este, EntityManager em, String iden) {
        initComponents();
        inicializarComponentes(este, em, iden);
        inicializarServicios();
        inicializarFormularios(este);
        keyListener();
    }

    public void inicializarServicios() {
        proveedoresJpaController = new ProveedoresJpaController(em);
    }

    public void inicializarComponentes(JInternalFrame este, EntityManager em, String iden) {
        this.em = em;
        this.origen = iden;

        tableModel();
    }

    public void inicializarFormularios(JInternalFrame este) {
        /*switch (origen) {
            case "proveedor":
                this.frmIngresoCompras = (FrmIngresoCompras) este;
                break;
            default:
                System.out.println("Procesar aqui defecto");
                break;
        }*/
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
        if (evt.getKeyCode() == VK_ESCAPE) {
            this.setVisible(false);
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupEstado = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabBuscar = new javax.swing.JTable();

        setTitle("Busqueda Registros");
        setMinimumSize(new java.awt.Dimension(515, 343));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblEstado.setText("Buscar:");

        txtFiltro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFiltroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstado)
                .addGap(18, 18, 18)
                .addComponent(txtFiltro)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tabBuscar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Código", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabBuscarKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabBuscar);
        if (tabBuscar.getColumnModel().getColumnCount() > 0) {
            tabBuscar.getColumnModel().getColumn(0).setMinWidth(100);
            tabBuscar.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabBuscar.getColumnModel().getColumn(0).setMaxWidth(100);
        }
        tabBuscar.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabBuscarKeyPressed
        /*try {
            if (evt.getKeyCode() == VK_ENTER) {
                int selectedRow = this.tabBuscar.getSelectedRow();
                this.codRetorno = model.getValueAt(selectedRow, 0).toString();

                //cierra la ventana de busqueda
                this.frmIngresoCompras.setValorRetornado(codRetorno, origen);

                this.setVisible(false);
                this.dispose();
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FrmBuscarGenerico.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }*/
        procesarKeyEvent(evt);
    }//GEN-LAST:event_tabBuscarKeyPressed

    private void txtFiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyPressed
        procesarKeyEvent(evt);
    }//GEN-LAST:event_txtFiltroKeyPressed

    private void txtFiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyTyped
        procesar();
    }//GEN-LAST:event_txtFiltroKeyTyped

    public String getCodRetorno() {
        return codRetorno;
    }

    public void tableModel() {
        model = (DefaultTableModel) this.tabBuscar.getModel();
        model.setNumRows(0);
    }

    public void procesar() {
        tableModel();
        switch (origen) {
            case "proveedor":
                buscarProveedor();
                break;
            default:
                System.out.println("Prcesar aqui defecto");
                break;
        }
    }

    public void buscarProveedor() {
        String filter = this.txtFiltro.getText();
        List<Proveedor> listaProveedores = null;

        if (filter.trim().isEmpty()) {
            listaProveedores = proveedoresJpaController.listarProveedores();
        } else {
            listaProveedores = proveedoresJpaController.listarProveedores(filter);
        }

        for (Proveedor prov : listaProveedores) {
            cargarGrilla(
                    String.valueOf(prov.getId()),
                    prov.getRuc() + " - " + prov.getNombre()
            );
        }
    }

    //carga la grilla con los valores filtrados y/o recuperados
    public void cargarGrilla(String id, String descripcion) {
        try {
            model.addRow(new Object[]{
                id,
                descripcion
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupEstado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTable tabBuscar;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
