
package py.com.ventu.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author cpatino
 */
@Entity
@Table(name = "sa_pendientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SaPendientes.findAll", query = "SELECT s FROM SaPendientes s"),
    @NamedQuery(name = "SaPendientes.findByNroTrans", query = "SELECT s FROM SaPendientes s WHERE s.nroTrans = :nroTrans"),
    @NamedQuery(name = "SaPendientes.findByRefTicket", query = "SELECT s FROM SaPendientes s WHERE s.refTicket = :refTicket"),
    @NamedQuery(name = "SaPendientes.findByCliente", query = "SELECT s FROM SaPendientes s WHERE s.cliente = :cliente"),
    @NamedQuery(name = "SaPendientes.findByRefTicketPendiente", query = "SELECT s FROM SaPendientes s WHERE s.refTicket = :refTicket and s.saldo > 0.0")})
public class SaPendientes implements Serializable {
    
    private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nro_trans")
    private Integer nroTrans; // nro de operacion interna
   
    @Column(name = "ref_caja")
    private Integer refCaja; // ref. caja de cobro
    
    @Column(name = "ref_ticket")
    private Integer refTicket; // ticket de venta
   
    @Column(name = "fec_doc")
    @Temporal(TemporalType.DATE) 
    private Date fecDoc; // fecha de operacion (venta)
    
    @Column(name = "vencimiento")
    @Temporal(TemporalType.DATE)
    private Date vencimiento; // fencimiento / si aplica
   
    @Column(name = "importe_origen")
    private Double importeOrigen; // monto original de la venta
    
    @Column(name = "saldo")
    private Double saldo; // saldo disponible a cancelar
    
    @Column(name = "cliente")
    private Integer cliente; 

    public SaPendientes() {
    }

    public SaPendientes(Integer nroTrans) {
        this.nroTrans = nroTrans;
    }

    public Integer getNroTrans() {
        return nroTrans;
    }

    public void setNroTrans(Integer nroTrans) {
        this.nroTrans = nroTrans;
    }

    public Integer getRefCaja() {
        return refCaja;
    }

    public void setRefCaja(Integer refCaja) {
        this.refCaja = refCaja;
    }

    public Integer getRefTicket() {
        return refTicket;
    }

    public void setRefTicket(Integer refTicket) {
        this.refTicket = refTicket;
    }

    public Date getFecDoc() {
        return fecDoc;
    }

    public void setFecDoc(Date fecDoc) {
        this.fecDoc = fecDoc;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Double getImporteOrigen() {
        return importeOrigen;
    }

    public void setImporteOrigen(Double importeOrigen) {
        this.importeOrigen = importeOrigen;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroTrans != null ? nroTrans.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaPendientes)) {
            return false;
        }
        SaPendientes other = (SaPendientes) object;
        if ((this.nroTrans == null && other.nroTrans != null) 
                || (this.nroTrans != null && !this.nroTrans.equals(other.nroTrans))) {
            return false;
        }
        return true;
    }   
}