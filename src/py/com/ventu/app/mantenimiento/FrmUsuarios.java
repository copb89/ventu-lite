
package py.com.ventu.app.mantenimiento;

//import py.com.ventu.app.util.buscadores.FrmBuscar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import py.com.ventu.controladores.UsuarioJpaController;
import py.com.ventu.controladores.exceptions.NonexistentEntityException;
import py.com.ventu.entidades.Estados;
import py.com.ventu.entidades.Usuario;

/**
 * @author cpatino
 */
public class FrmUsuarios extends javax.swing.JInternalFrame {

    private EntityManager em;
    private Usuario usuarios = new Usuario();
    private UsuarioJpaController service;
    private List<Usuario> listaUsuarios;
    
    private int cantUsuarios;
    private int posLista;    
    
    private Estados estado;
    private Usuario usuario;
    
    /**
     * Crea un nuevo Formulario FrmUsuario.
     */
    public FrmUsuarios(EntityManager em, Usuario user) {
        initComponents();
        
        this.em = em;
        this.usuario = user;
        
        service = new UsuarioJpaController(em);
         estado = Estados.CANCELADO;

        cargarListaRegistros(0);
        desplegarPrimerRegistro();
    }
    
    /**
     * Carga los Campos en base al valor del objeto "Usuario".
     **/
    private void cargarCampos(){
        //txtCodigo.setText(usuarios.getId().toString());
        txtNombre.setText(usuarios.getNombre());
        txtUsuario.setText(usuarios.getUsername());
        txtContrasena.setText(usuarios.getContrasena());
        if (usuarios.getEsAdmin()) {
            radioBtnActivo.setEnabled(true);
            radioBtnActivo.doClick();            
            radioBtnInactivo.setEnabled(false);            
        } else {
            radioBtnInactivo.setEnabled(true);
            radioBtnInactivo.doClick();            
            radioBtnActivo.setEnabled(false);
        }
        // bloquear Campos        
        txtNombre.setEditable(false);        
        txtUsuario.setEditable(false);
        txtContrasena.setEditable(false);
    }
    
    /**
     * Inicializa los Campos para ser cargados
     **/
    public void inicializarComponentes(){
        usuarios = new Usuario();
        this.txtNombre.setText("");
        this.txtUsuario.setText("");
        this.txtContrasena.setText("");  
        this.btnGroupEstado.clearSelection();
        this.radioBtnActivo.setSelected(true);
        desbloquearCampos();
    }
    
    /**
     * Recupera la lista de registros de la base de datos.
     **/
    public void cargarListaRegistros(int busco){
         listaUsuarios = service.listarUsuarios();
         cantUsuarios = listaUsuarios.size();
         
         if(listaUsuarios.size() > 0){
            posLista = busco;

            usuarios = listaUsuarios.get(posLista);
           // txtCodigo.setText(usuarios.getId().toString());
            txtNombre.setText(usuarios.getNombre());            
            txtUsuario.setText(usuarios.getUsername());
            txtContrasena.setText(usuarios.getContrasena());
            if (usuarios.getEsAdmin()) {
                radioBtnActivo.doClick();
                radioBtnInactivo.setEnabled(false);
            } else {
                radioBtnInactivo.doClick();
                radioBtnActivo.setEnabled(false);
            }
        }
    }

    /**
     * Despliega el primer registro de la lista.
     **/
    private void desplegarPrimerRegistro() {
        if(listaUsuarios.size() > 0){
            posLista = 0;

            usuarios = listaUsuarios.get(posLista);
           // txtCodigo.setText(usuarios.getId().toString());
            txtNombre.setText(usuarios.getNombre());            
            txtUsuario.setText(usuarios.getUsername());
            txtContrasena.setText(usuarios.getContrasena());
            if (usuarios.getEsAdmin()) {
                radioBtnActivo.doClick();
                radioBtnInactivo.setEnabled(false);
            } else {
                radioBtnInactivo.doClick();
                radioBtnActivo.setEnabled(false);
            }
        }
    }
   
    /**
     * Desbloquea los campos para ser editados.
     **/
    private void desbloquearCampos(){        
        txtNombre.setEditable(true);
        
        if(estado.equals(estado.NUEVO)){
            txtUsuario.setEditable(true);
        }
        
        txtContrasena.setEditable(true);
        radioBtnActivo.setEnabled(true);
        radioBtnInactivo.setEnabled(true);
    }
    
    /**
     * Bloquea la utilización de los botones innecesarios durante la carga de un nuevo registro
     * o la edición de uno existente.
     **/
    private void bloquearBotones(){
        cmdNuevo.setEnabled(false);
        cmdEditar.setEnabled(false);
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
        cmdEditar.setEnabled(true);
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

        btnGroupEstado = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        radioBtnActivo = new javax.swing.JRadioButton();
        radioBtnInactivo = new javax.swing.JRadioButton();
        lblContrasena = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        chkBoxMostrarContrasena = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        tbrHerramientas = new javax.swing.JToolBar();
        cmdNuevo = new javax.swing.JButton();
        cmdEditar = new javax.swing.JButton();
        cmdGuardar = new javax.swing.JButton();
        cmdCancelar = new javax.swing.JButton();
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
        setTitle("Usuarios");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNombre.setText("Nombre:");

        lblEstado.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblEstado.setText("Grupo:");

        btnGroupEstado.add(radioBtnActivo);
        radioBtnActivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        radioBtnActivo.setSelected(true);
        radioBtnActivo.setText("Normal");
        radioBtnActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnActivoActionPerformed(evt);
            }
        });

        btnGroupEstado.add(radioBtnInactivo);
        radioBtnInactivo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        radioBtnInactivo.setText("Administrador");
        radioBtnInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBtnInactivoActionPerformed(evt);
            }
        });

        lblContrasena.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblContrasena.setText("Contraseña:");

        txtContrasena.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtContrasena.setToolTipText("Contraseña del usuario");
        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });

        lblUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblUsuario.setText("Usuario:");

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUsuario.setToolTipText("Usuario para ingreso al sistema");
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNombre.setToolTipText("Nombre y Apellido del usuario");

        chkBoxMostrarContrasena.setText("Mostrar Contraseña");
        chkBoxMostrarContrasena.setToolTipText("Mostrar u ocultar la contraseña ingresada");
        chkBoxMostrarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBoxMostrarContrasenaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        jLabel1.setText("* Obligatorio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEstado, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radioBtnActivo)
                        .addGap(18, 18, 18)
                        .addComponent(radioBtnInactivo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(txtUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkBoxMostrarContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContrasena)
                    .addComponent(chkBoxMostrarContrasena))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBtnActivo)
                    .addComponent(lblEstado)
                    .addComponent(radioBtnInactivo))
                .addGap(4, 4, 4))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbrHerramientas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tbrHerramientas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNuevoActionPerformed
        inicializarComponentes();
        desbloquearCampos();
        this.txtNombre.grabFocus();
        estado = Estados.NUEVO;
        bloquearBotones();
    }//GEN-LAST:event_cmdNuevoActionPerformed

    private void cmdEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditarActionPerformed
        if (listaUsuarios != null){
            desbloquearCampos();
            this.txtNombre.grabFocus();
            estado = Estados.EDITAR;
            bloquearBotones();
        }
    }//GEN-LAST:event_cmdEditarActionPerformed

    private void cmdGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdGuardarActionPerformed
      
        if (!this.txtUsuario.getText().trim().equals("") || !this.txtUsuario.getText().isEmpty()) {

        // verifica si existe el usuario en la base de datos y guarda los datos. 
            // En caso contrario, despliega un mensaje de usuario existente.
            Usuario user = service.buscarUsuariosPorUsuario(this.txtUsuario.getText().trim());

            if (user == null) { // usuario no encontrado. Nuevo registro.
                guardar();
            } else { // En caso que exista.
                if (estado.equals(estado.EDITAR)) { // Editar con user existente.
                    guardar();
                } else { // Registro nuevo para usuario existente.
                    JOptionPane.showMessageDialog(null, "El Username ingresado ya está asignado a otro usuario.",
                            "Usuario.", JOptionPane.ERROR_MESSAGE);
                    this.txtUsuario.grabFocus();
                    this.txtUsuario.selectAll();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "El campo USUARIO no pueda esta vacío. Ingrese un valor.",
                            "Usuario.", JOptionPane.ERROR_MESSAGE);
        }
        
       // if (service.buscarUsuariosPorUsuario(this.txtUsuario.getText().trim()) == null){    
       /* if (user == null ){
            usuarios.setNombre(this.txtNombre.getText());
            usuarios.setUsuario(txtUsuario.getText());
            usuarios.setContrasena(this.txtContrasena.getText());
            usuarios.setEsAdmin(radioBtnActivo.isSelected());
            usuarios.setFechaMod(new Date());
            
            try {
                service.guardar(usuarios);
                cargarListaRegistros(0);
                estado = Estados.CANCELADO;
                DesbloquearBotones();
                cmdUltimo.doClick();
            } catch (NonexistentEntityException nex) {
                JOptionPane.showMessageDialog(null, "El Usuario no existe en la base de datos." 
                         + "\n" + nex.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);
                //Logger.getLogger(FrmUsuarios.class.getName()).log(Level.SEVERE, null, ex);        
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Se ha producido un error al guardar el registro. "
                                + "\n" + ex.getMessage(),
                                "Usuario", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El Username ingresado ya está asignado a otro usuario.",
                                "Usuario.", JOptionPane.ERROR_MESSAGE);
            this.txtUsuario.grabFocus();
            this.txtUsuario.selectAll();
            //Logger.getLogger(FrmUsuarios.class.getName()).log(Level.SEVERE, "El Usuario ya existe en la base de datos. Intente otro valor para el campo Usuario. ");    
        }*/
            
    }//GEN-LAST:event_cmdGuardarActionPerformed

    private void guardar() {
        try {
            usuarios.setNombre(this.txtNombre.getText());
            usuarios.setUsuario(txtUsuario.getText());
            usuarios.setContrasena(this.txtContrasena.getText());
            usuarios.setEsAdmin(radioBtnActivo.isSelected());
            usuarios.setFechaMod(new Date());

            service.guardar(usuarios);
            cargarListaRegistros(0);
            estado = Estados.CANCELADO;
            DesbloquearBotones();
            cmdUltimo.doClick();
        } catch (NonexistentEntityException nex) {
            JOptionPane.showMessageDialog(null, "El Usuario no existe en la base de datos."
                    + "\n" + nex.getMessage(), "Usuario", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error al guardar el registro. "
                    + "\n" + ex.getMessage(),
                    "Usuario", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FrmUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void cmdCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelarActionPerformed
        if (posLista > 0){
            usuarios = listaUsuarios.get(posLista);
            cargarCampos();
        }
        DesbloquearBotones();
    }//GEN-LAST:event_cmdCancelarActionPerformed

    private void cmdBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBuscarActionPerformed
//        FrmBuscar busco = new FrmBuscar(this,em,"usuarios");
        // busco.setVisible(true);
    }//GEN-LAST:event_cmdBuscarActionPerformed

    private void cmdPrimeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrimeroActionPerformed
        if (cantUsuarios > 0) {
            usuarios = listaUsuarios.get(0);
            cargarCampos();
            posLista = 0;
        }
    }//GEN-LAST:event_cmdPrimeroActionPerformed

    private void cmdAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAnteriorActionPerformed
        if (posLista > 0) {
            usuarios = listaUsuarios.get(posLista - 1);
            cargarCampos();
            posLista--;
        }
    }//GEN-LAST:event_cmdAnteriorActionPerformed

    private void cmdSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSiguienteActionPerformed
        if (posLista < cantUsuarios-1) {
            usuarios = listaUsuarios.get(posLista + 1);
            cargarCampos();
            posLista++;
        }
    }//GEN-LAST:event_cmdSiguienteActionPerformed

    private void cmdUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUltimoActionPerformed
        if (cantUsuarios > 0) {
            usuarios = listaUsuarios.get(cantUsuarios - 1);
            cargarCampos();
            posLista = cantUsuarios - 1;
        }
    }//GEN-LAST:event_cmdUltimoActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void chkBoxMostrarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBoxMostrarContrasenaActionPerformed
        if(chkBoxMostrarContrasena.isSelected()){
            char c = 0;
            txtContrasena.setEchoChar(c);
        } else {
            char c = 42;
            txtContrasena.setEchoChar(c);
        }       
    }//GEN-LAST:event_chkBoxMostrarContrasenaActionPerformed

    private void radioBtnActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBtnActivoActionPerformed

    private void radioBtnInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBtnInactivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBtnInactivoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupEstado;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkBoxMostrarContrasena;
    private javax.swing.JButton cmdAnterior;
    private javax.swing.JButton cmdBuscar;
    private javax.swing.JButton cmdCancelar;
    private javax.swing.JButton cmdEditar;
    private javax.swing.JButton cmdGuardar;
    private javax.swing.JButton cmdNuevo;
    private javax.swing.JButton cmdPrimero;
    private javax.swing.JButton cmdSiguiente;
    private javax.swing.JButton cmdUltimo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panNavegacion;
    private javax.swing.JRadioButton radioBtnActivo;
    private javax.swing.JRadioButton radioBtnInactivo;
    private javax.swing.JToolBar tbrHerramientas;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
