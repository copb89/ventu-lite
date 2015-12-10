
package py.com.ventu.app.mantenimiento;

//import py.com.ventu.app.util.buscadores.FrmBuscar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import py.com.ventu.controladores.VendedorJpaController;
import py.com.ventu.controladores.exceptions.NonexistentEntityException;
import py.com.ventu.entidades.Usuario;
import py.com.ventu.entidades.Vendedor;

/**
 * @author cpatino
 */
public class FrmVendedores extends javax.swing.JInternalFrame {

    private EntityManager em;
    private VendedorJpaController service;
    // Objeto de Entidad Vendedores que sera usada en todo el formulario
    //si es necesario se instancia de vuelta para setear en null los valores.
    private Vendedor vendedores = new Vendedor();
    private List<Vendedor> listaVendedores;
    private int cantVendedores;
    private int posLista;
    
    private Usuario usuario;
    
    /**
     * Creates new form FrmVendedores
     */
    public FrmVendedores(EntityManager em, Usuario user) {
        initComponents();
        
        this.em = em;
        this.usuario = user;

        service = new VendedorJpaController(em);

        cargarListaRegistros(0);
        desplegarPrimerRegistro();
    }

    /**
     * Carga los Campos en base al valor del objeto "Vendores".
     **/
    private void cargarCampos(){
        txtCodigo.setText(vendedores.getId().toString());
        txtNombre.setText(vendedores.getNombre());

        // bloquar Campos        
        txtNombre.setEditable(false);
    }
    
    
    /**
     * Inicializa los Campos para ser cargados
     **/
    public void inicializarComponentes(){
        vendedores = new Vendedor();
        this.txtCodigo.setText("");
        this.txtNombre.setText("");

        desbloquearCampos();
    }
    
    /**
     * Recupera la lista de registros de la base de datos.
     **/
    public void cargarListaRegistros(int busco){
         listaVendedores = service.listarVendedores();
         cantVendedores = listaVendedores.size();
         if(listaVendedores.size() > 0){
            posLista = busco;

            vendedores = listaVendedores.get(posLista);
            txtCodigo.setText(vendedores.getId().toString());
            txtNombre.setText(vendedores.getNombre());

            //En el caso que se encuentre con elementos la tabla , 
            //activa el boton Editar y Eliminar puesto que existe elemento a editar
            cmdEditar.setEnabled(true);
            cmdEliminar.setEnabled(true);
        }else{ //En el caso que se encuentre vacia la tabla , 
            //desactiva el boton Editar y Eliminar puesto que no existe elemento a editar
            cmdEditar.setEnabled(false);
            cmdEliminar.setEnabled(false);
        }
    }

    /**
     * Despliega el primer registro de la lista.
     **/
    private void desplegarPrimerRegistro() {
        if(listaVendedores.size() > 0){
            posLista = 0;

            vendedores = listaVendedores.get(posLista);
            txtCodigo.setText(vendedores.getId().toString());
            txtNombre.setText(vendedores.getNombre());
        }
    }
  
    /**
     * Desbloquea los campos para ser editados.
     **/
    private void desbloquearCampos(){        
        txtNombre.setEditable(true);
    }
    
    /**
     * Bloquea la utilización de los botones innecesarios durante la carga de un nuevo registro
     * o la edición de uno existente.
     **/
    private void bloquearBotones(){
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
     * Desbloquea la utilización de los botones bloquedos durante
     * la creación de un nuevo registro o su edición.
     **/
    private void DesbloquearBotones(){
        cmdNuevo.setEnabled(true);
        if(listaVendedores.size() > 0){
            //En el caso que se encuentre con elementos la tabla , 
            //activa el boton Editar y Eliminar puesto que existe elemento a editar
            cmdEditar.setEnabled(true);
            cmdEliminar.setEnabled(true);
        }else{ //En el caso que se encuentre vacia la tabla , 
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

        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        lblNombre1 = new javax.swing.JLabel();
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
        setTitle("Vendedores");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombre.setText("Nombre:");

        txtNombre.setToolTipText("Nombre del vendedor");

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(java.awt.Color.lightGray);

        lblNombre1.setText("Código:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(panNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cmdPrimero)
                .addComponent(cmdAnterior)
                .addComponent(cmdSiguiente)
                .addComponent(cmdUltimo))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbrHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(tbrHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(panNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNuevoActionPerformed
        inicializarComponentes();
        desbloquearCampos();
        this.txtNombre.grabFocus();
        bloquearBotones();
    }//GEN-LAST:event_cmdNuevoActionPerformed

    private void cmdEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditarActionPerformed
        if (listaVendedores != null){
            desbloquearCampos();
            this.txtNombre.grabFocus();
            bloquearBotones();
        }
    }//GEN-LAST:event_cmdEditarActionPerformed

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
        vendedores.setNombre(this.txtNombre.getText());
        vendedores.setFechaMod(new Date());
        vendedores.setUsuarioMod(usuario.getUsername());
        try {
            service.guardar(vendedores);
            cargarListaRegistros(0);
            DesbloquearBotones();
            cmdUltimo.doClick();
        } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(null, "El registro de Vendedores no existe en la base de datos. " ,
                                "Vendedor", JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(FrmVendedores.class.getName()).log(Level.SEVERE, "El registro de Vendedores no existe en la base de datos. ", ex);        
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Se ha producido un error al guardar el registro. "
                                + "\n" + ex.getMessage(),
                                "Vendedor", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmVendedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdGuardarActionPerformed

    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        if (posLista > 0){
            vendedores = listaVendedores.get(posLista);
            cargarCampos();
        }
        DesbloquearBotones();
    }//GEN-LAST:event_cmdCancelarActionPerformed

    private void cmdEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEliminarActionPerformed
        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "Eliminación de Registro",
            "Desea eliminar este registro.", JOptionPane.YES_NO_OPTION);
        if (showConfirmDialog == 0) {
            try {
                service.borrar(vendedores);
                cargarListaRegistros(0);
                desplegarPrimerRegistro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al eliminar el registro. \n" + ex.getMessage(),
                    "Vendedor", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmVendedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cmdEliminarActionPerformed

    private void cmdBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActionPerformed
       //FrmBuscar busco = new FrmBuscar(this,em,"vendedores");
       //  busco.setVisible(true);
    }//GEN-LAST:event_cmdBuscarActionPerformed

    private void cmdPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrimeroActionPerformed
        if (cantVendedores > 0) {
            vendedores = listaVendedores.get(0);
            cargarCampos();
            posLista = 0;
        }
    }//GEN-LAST:event_cmdPrimeroActionPerformed

    private void cmdAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnteriorActionPerformed
        if (posLista > 0) {
            vendedores = listaVendedores.get(posLista - 1);
            cargarCampos();
            posLista--;
        }
    }//GEN-LAST:event_cmdAnteriorActionPerformed

    private void cmdSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSiguienteActionPerformed
        if (posLista < cantVendedores-1) {
            vendedores = listaVendedores.get(posLista + 1);
            cargarCampos();
            posLista++;
        }
    }//GEN-LAST:event_cmdSiguienteActionPerformed

    private void cmdUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUltimoActionPerformed
        if (cantVendedores > 0) {
            vendedores = listaVendedores.get(cantVendedores - 1);
            
            cargarCampos();
            posLista = cantVendedores - 1;
        }
    }//GEN-LAST:event_cmdUltimoActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JPanel panNavegacion;
    private javax.swing.JToolBar tbrHerramientas;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
