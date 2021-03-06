
package py.com.ventu.app.compras;

import static java.awt.event.KeyEvent.VK_ENTER;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import py.com.ventu.controladores.ProductosJpaController;
import py.com.ventu.controladores.TipoProductoJpaController;
import py.com.ventu.entidades.Producto;
import py.com.ventu.entidades.TipoProducto;
import py.com.ventu.entidades.Usuario;
import py.com.ventu.util.EditorCeldas;

/**
 * @author progress
 */
public class FrmAjustePrecio extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmAjustePrecio
     */
    private Usuario usuarios;
    private TipoProducto categoria;
    private EntityManager em;
    private ProductosJpaController productoService;
    private TipoProductoJpaController tipoProductoService;

    private List<TipoProducto> listaCategorias;
    private List<Producto> listaProductos;
    
    // Variables de la tabla
    private DefaultTableModel model;
    private Object[] fila;

    public FrmAjustePrecio(EntityManager ema, Usuario user) {
        initComponents();

        this.em = ema;
        this.usuarios = user;

        productoService = new ProductosJpaController(em);
        tipoProductoService = new TipoProductoJpaController(em);
        tableModel();
        cargarComboBoxCategoria();
        
        this.txtFilter.grabFocus();
    }

    private void cargarComboBoxCategoria() {
        //Carga Surcursal
        cmbBoxCategoria.removeAllItems();
        listaCategorias = tipoProductoService.listarTipoProductos();
        Iterator<TipoProducto> iterator = listaCategorias.iterator();
        while (iterator.hasNext()) {
            TipoProducto a = iterator.next();
            cmbBoxCategoria.addItem(a.getDescripcion());
        }
        cmbBoxCategoria.addItem("Todos");
    }

    public void tableModel() {
        //inicializacion de la grilla
        model = (DefaultTableModel) tabProductos.getModel();
        model.setRowCount(0);

        tabProductos.getModel().addTableModelListener(tabProductos);
    }
    
     // aplica el editor de celdas sobre escrito
    public void formatearCeldas(){
        TableColumn col2 = tabProductos.getColumnModel().getColumn(2);
        col2.setCellEditor(new EditorCeldas());
    }

    /**
     * Recibe el id del producto seleccionado desde el FrmBucarProdCompraVenta.
     *
     */
    public void cargaGrilla(Integer IdProducto) {
        System.out.println("Nuevo elemento en la grilla");
        for (int i = 0; i < this.tabProductos.getRowCount(); i++) {
            if (IdProducto.equals(Integer.parseInt(tabProductos.getValueAt(i, 0).toString()))) {
                JOptionPane.showMessageDialog(null, "El producto seleccionado ya fue Seleccionado."
                        + "\n" + "Atencion: No se permite repetidos.", "Ajuste Precio", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (IdProducto >= 0) {
            productoService = new ProductosJpaController(em);
            Producto producto = productoService.buscarProductos(IdProducto);
            //carga en la grilla
            cargarDetalleGrilla(producto);
            //aplica focus a esta celda
            tabProductos.changeSelection(model.getRowCount() - 1, 3, false, false);
        }
    }

    /**
     * Carga el Producto seleccionado en la grilla.
     *
     */
    public void cargarDetalleGrilla(Producto prod) {
        Double precioNuevo = prod.getPrecioVenta()+ (prod.getPrecioVenta()* (Double.parseDouble(porcentaje.getValue().toString()) / 100));
        redondear(precioNuevo,0);
        try {
            model.addRow(new Object[]{
                prod.getCodigo(), //0 Codigo del Producto
                prod.getDescripcion(), //1 descripcion Producto
                precioNuevo,//2 Precio Nuevo
                prod.getPrecioVenta(), //3 Precio actual del producto
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al agregar el producto a la grilla."
                    + "\n" + ex.getMessage(), "Ajuste Stock", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    public double redondear(Double importe, int decimales){
        return Math.round(importe*Math.pow(10,decimales))/Math.pow(10,decimales);
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
        jLabel2 = new javax.swing.JLabel();
        cmbBoxCategoria = new javax.swing.JComboBox();
        porcentaje = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        cmdQuitar = new javax.swing.JButton();
        Previsualizacion = new javax.swing.JButton();
        txtFilter = new javax.swing.JTextField();
        lblEstado1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabProductos = new javax.swing.JTable();
        Aplicar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Ajuste de Precios de Productos");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setAutoscrolls(true);

        jLabel2.setText("Categoría de Productos");

        cmbBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Ajuste %");

        cmdQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/py/com/progress/sgf/imagenes/quitar_16.png"))); // NOI18N
        cmdQuitar.setText("Quitar");
        cmdQuitar.setToolTipText("Quitar elemento de la lista de productos");
        cmdQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdQuitarActionPerformed(evt);
            }
        });

        Previsualizacion.setText("Previsualización");
        Previsualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrevisualizacionActionPerformed(evt);
            }
        });

        txtFilter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFilterKeyPressed(evt);
            }
        });

        lblEstado1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblEstado1.setText("Producto:");

        tabProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Código", "Descripción", "Nuevo Precio", "Actual Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabProductos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabProductos.setRowHeight(22);
        tabProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabProductos);
        if (tabProductos.getColumnModel().getColumnCount() > 0) {
            tabProductos.getColumnModel().getColumn(0).setMaxWidth(60);
            tabProductos.getColumnModel().getColumn(2).setMaxWidth(70);
            tabProductos.getColumnModel().getColumn(3).setMaxWidth(70);
        }

        Aplicar.setText("Aplicar");
        Aplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AplicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Aplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblEstado1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(260, 260, 260)
                                        .addComponent(Previsualizacion)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(porcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Previsualizacion))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado1)
                    .addComponent(cmdQuitar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Aplicar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdQuitarActionPerformed
        int selectedRow = tabProductos.getSelectedRow();
        quitarElemento(selectedRow);
    }//GEN-LAST:event_cmdQuitarActionPerformed
    /**
     * Quita el elemento seleccionado de la grilla.
     *
     */
    private void quitarElemento(int filaSel) {
        try {
            if (filaSel < 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un registro para eliminar.",
                        "Ajuste Precio", JOptionPane.ERROR_MESSAGE);
            } else {
                model.removeRow(filaSel);
                this.txtFilter.grabFocus();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al quitar artículo de la lista. "
                    + e.getMessage(), "Ajuste Precio", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void txtFilterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            String selectedRow = this.txtFilter.getText(); //se obtiene el valor de la fila seleccionada (empieza en 0)
//            FrmBuscarProdCompraVenta vProd = new FrmBuscarProdCompraVenta(this, em, "ajustesprecio", selectedRow, usuarios);

            //vProd.setVisible(true);
            this.txtFilter.setText("");
        }
    }//GEN-LAST:event_txtFilterKeyPressed

    private void tabProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabProductosKeyPressed

    }//GEN-LAST:event_tabProductosKeyPressed

    private void PrevisualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrevisualizacionActionPerformed
        String Categoria = (String) cmbBoxCategoria.getSelectedItem();
        Double Ajuste = Double.parseDouble(porcentaje.getValue().toString());
        //Double Ajuste = Double.parseDouble(this.porcentaje.toString());
        if (Categoria.equals("Todos")) {
            listaProductos = productoService.listarProductos();
        } else {
            categoria = tipoProductoService.buscarTipoProductosPorDescripcion(Categoria);
            /*List<Productos> listaProductosTodos = productoService.listarProductos();
            
            List<Productos> listaProductosCategoria = new ArrayList();
            for (Productos prod : listaProductosTodos) {
                ///ACAAAA o mejor crear un query con solo los productos de la categoria seleccionada
                System.out.println(categoria.getId() +" "+ prod.getTipoProducto().getId());
                if (prod.getTipoProducto().getId().equals(categoria.getId())) {
                    listaProductosCategoria.add(prod);
                }
            }
            listaProductos = listaProductosCategoria;*/
            listaProductos= productoService.ListarProductosPorTipoProducto(categoria.getId());
        }
        rellenarTabla(Ajuste);
    }//GEN-LAST:event_PrevisualizacionActionPerformed

    private void AplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AplicarActionPerformed
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Desea procesar el ajuste de precios?",
                "Ajuste de Precios", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == 0) {
            //graba el detalle de la lista
            for (int i = 0; i < this.tabProductos.getRowCount(); i++) {
                try {
                    Producto prod = productoService.buscarProductos(Integer.parseInt(tabProductos.getValueAt(i, 0).toString()));

                    prod.setPrecioVenta(Double.parseDouble(tabProductos.getValueAt(i, 2).toString()));
                    prod.setUsuarioMod(usuarios.getUsername());
                    prod.setFechaMod(new Date());
                    productoService.guardar(prod);
                    //Como que siempre se vuelve a eliminar y cargar
                } catch (Exception e) {
                    System.out.println("Error en el elemento de la grilla " + i);
                    JOptionPane.showMessageDialog(null, "Ocurrió un error al insertar el registro. " + i + e.getMessage(),
                            "Ajuste de Precio", JOptionPane.ERROR_MESSAGE);
                   // Logger.getLogger(FrmEnvioSucursal.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            JOptionPane.showMessageDialog(null, "Ajuste de Precio generado satisfactoriamente.",
                    "Ajuste de Precio", JOptionPane.INFORMATION_MESSAGE);
            tableModel();
            this.porcentaje.setValue(0);
        }
    }//GEN-LAST:event_AplicarActionPerformed

    private void rellenarTabla(Double Ajuste) {
        if (listaProductos != null) {
            tableModel();
            //Se itera por cada registro de la lista y se carga la tabla
            Producto prod;
            Iterator<Producto> it = listaProductos.iterator();
            model.setRowCount(0);

            try {
                while (it.hasNext()) {
                    prod = it.next();
                    Double precioNuevo = prod.getPrecioVenta()+ (prod.getPrecioVenta()* (Double.parseDouble(porcentaje.getValue().toString()) / 100));
                    if (prod != null) {
                        model.addRow(new Object[]{
                            prod.getCodigo(), //0 Codigo del Producto
                            prod.getDescripcion(), //1 descripcion Producto
                            precioNuevo,//2 Precio Nuevo
                            prod.getPrecioVenta(), //3 Precio actual del producto
                        });
                    } else {
                        System.out.println("Ocurrio un error" + " ");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aplicar;
    private javax.swing.JButton Previsualizacion;
    private javax.swing.JComboBox cmbBoxCategoria;
    private javax.swing.JButton cmdQuitar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado1;
    private javax.swing.JSpinner porcentaje;
    private javax.swing.JTable tabProductos;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
