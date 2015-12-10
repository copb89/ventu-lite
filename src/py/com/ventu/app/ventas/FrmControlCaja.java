package py.com.ventu.app.ventas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import py.com.ventu.entidades.Usuario;

/**
 * @author cpatino
 */
public class FrmControlCaja extends javax.swing.JInternalFrame {

    private EntityManager em;

    private Usuario usuario;

    private Date fechaDesde;
    private Date fechaHasta;

    //para mantener un log de registro
    Logger log;

    /**
     * Creates new form FrmControlCaja
     */
    public FrmControlCaja() {
        initComponents();
    }

    public FrmControlCaja(EntityManager ema, Usuario user, Logger logger) {
        initComponents();

        this.em = ema;
        this.usuario = user;
        this.log = logger;
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
        jLabel2 = new javax.swing.JLabel();
        chkControlCajaDetalle = new javax.swing.JCheckBox();
        txtFechaDesde = new com.toedter.calendar.JDateChooser();
        txtFechaHasta = new com.toedter.calendar.JDateChooser();
        cmdProcesar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Control de Caja");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Desde:");

        jLabel2.setText("Hasta:");

        chkControlCajaDetalle.setText("Ver en Detalle");
        chkControlCajaDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkControlCajaDetalleActionPerformed(evt);
            }
        });

        txtFechaDesde.setMaxSelectableDate(new Date());

        txtFechaHasta.setMaxSelectableDate(new Date());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(chkControlCajaDetalle))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(chkControlCajaDetalle)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        cmdProcesar.setText("Procesar");
        cmdProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProcesarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdProcesar)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProcesarActionPerformed
        fechaDesde = txtFechaDesde.getDate();
        fechaHasta = txtFechaHasta.getDate();
        if(fechaHasta.before(fechaDesde)){
            JOptionPane.showMessageDialog(null, "Ocurrió un error con el ingreso de fechas "
                    + "\n" + "La fecha Desde debe ser menor que la fecha",
                    "Control Caja", JOptionPane.ERROR_MESSAGE);
        }
        
        imprimir();
    }//GEN-LAST:event_cmdProcesarActionPerformed

    private void chkControlCajaDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkControlCajaDetalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkControlCajaDetalleActionPerformed

    public void imprimir() {
        try {
            Connection conexion;
            
            String reporte = "py/com/progress/sgf/reportes/ControlDeCaja.jasper";
            
            if(chkControlCajaDetalle.isSelected()){
                reporte = "py/com/progress/sgf/reportes/ControlDeCajaDetalle.jasper";
            }
            
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sgf", "postgres", "postgres");
            Map parametros = new HashMap();
            //parametros que enviamos al report.
            parametros.put("fechaDesde", fechaDesde);
            parametros.put("fechaHasta", fechaHasta);
            parametros.put("usuario", usuario.getUsername());
            JasperReport elReporte = (JasperReport) JRLoader.loadObject(ClassLoader.getSystemResource(reporte));
            JasperPrint imprimir = JasperFillManager.fillReport(elReporte, parametros, conexion);
            JasperViewer visor = new JasperViewer(imprimir, false);
            visor.setTitle("Control de Caja");
            visor.setVisible(true);
            conexion.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al imprimir resumen de caja "
                    + "\n" + ex.getMessage() + "\n" + ex.getCause(),
                    "Ventas", JOptionPane.ERROR_MESSAGE);
            log.info("Control de Caja" + "; Proceso: ControlDeCaja ; " + ex.getMessage() + " ; CAUSA: " + ex.getCause());
            ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkControlCajaDetalle;
    private javax.swing.JButton cmdProcesar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser txtFechaDesde;
    private com.toedter.calendar.JDateChooser txtFechaHasta;
    // End of variables declaration//GEN-END:variables
}