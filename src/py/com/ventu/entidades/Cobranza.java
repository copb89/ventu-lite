
package py.com.ventu.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cobranza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cobranza.findAll", query = "SELECT c FROM Cobranza c"),
    @NamedQuery(name = "Cobranza.findByNroTrans", query = "SELECT c FROM Cobranza c WHERE c.nroTrans = :nroTrans"),
    @NamedQuery(name = "Cobranza.findByCodCliente", query = "SELECT c FROM Cobranza c WHERE c.codCliente = :codCliente"),
    @NamedQuery(name = "Cobranza.findByImporteTotal", query = "SELECT c FROM Cobranza c WHERE c.importeTotal = :importeTotal"),
    @NamedQuery(name = "Cobranza.findByUsuarioMod", query = "SELECT c FROM Cobranza c WHERE c.usuarioMod = :usuarioMod"),
    @NamedQuery(name = "Cobranza.findByNroRecibo", query = "SELECT c FROM Cobranza c WHERE c.nroRecibo = :nroRecibo"),
    @NamedQuery(name = "Cobranza.findByFecha", query = "SELECT c FROM Cobranza c WHERE c.fecha = :fecha")})
public class Cobranza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nro_trans")
    private Integer nroTrans;
    @Column(name = "cod_cliente")
    private Integer codCliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe_total")
    private Double importeTotal;
    @Column(name = "usuario_mod")
    private String usuarioMod;
    @Column(name = "nro_recibo")
    private Integer nroRecibo;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "caja", referencedColumnName = "id")
    @ManyToOne
    private Caja caja;

    public Cobranza() {
    }

    public Cobranza(Integer nroTrans) {
        this.nroTrans = nroTrans;
    }

    public Integer getNroTrans() {
        return nroTrans;
    }

    public void setNroTrans(Integer nroTrans) {
        this.nroTrans = nroTrans;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(String usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public Integer getNroRecibo() {
        return nroRecibo;
    }

    public void setNroRecibo(Integer nroRecibo) {
        this.nroRecibo = nroRecibo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
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
        if (!(object instanceof Cobranza)) {
            return false;
        }
        Cobranza other = (Cobranza) object;
        if ((this.nroTrans == null && other.nroTrans != null) || (this.nroTrans != null && !this.nroTrans.equals(other.nroTrans))) {
            return false;
        }
        return true;
    }
    
}
