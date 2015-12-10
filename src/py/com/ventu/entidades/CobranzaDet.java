
package py.com.ventu.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "cobranza_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CobranzaDet.findAll", query = "SELECT c FROM CobranzaDet c"),
    @NamedQuery(name = "CobranzaDet.findByNroRecibo", query = "SELECT c FROM CobranzaDet c WHERE c.cobranzaDetPK.nroRecibo = :nroRecibo"),
    @NamedQuery(name = "CobranzaDet.findByLinea", query = "SELECT c FROM CobranzaDet c WHERE c.cobranzaDetPK.linea = :linea"),
    @NamedQuery(name = "CobranzaDet.findByRefOperacion", query = "SELECT c FROM CobranzaDet c WHERE c.refOperacion = :refOperacion"),
    @NamedQuery(name = "CobranzaDet.findByTicket", query = "SELECT c FROM CobranzaDet c WHERE c.ticket = :ticket"),
    @NamedQuery(name = "CobranzaDet.findByFechaVto", query = "SELECT c FROM CobranzaDet c WHERE c.fechaVto = :fechaVto"),
    @NamedQuery(name = "CobranzaDet.findByImporte", query = "SELECT c FROM CobranzaDet c WHERE c.importe = :importe"),
    @NamedQuery(name = "CobranzaDet.findByCodSucFac", query = "SELECT c FROM CobranzaDet c WHERE c.codSucFac = :codSucFac"),
    @NamedQuery(name = "CobranzaDet.findByCodEmiFac", query = "SELECT c FROM CobranzaDet c WHERE c.codEmiFac = :codEmiFac"),
    @NamedQuery(name = "CobranzaDet.findByNroDocum", query = "SELECT c FROM CobranzaDet c WHERE c.nroDocum = :nroDocum")})
public class CobranzaDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CobranzaDetPK cobranzaDetPK;
    @Column(name = "ref_operacion")
    private Integer refOperacion;
    @Column(name = "ticket")
    private Integer ticket;
    @Column(name = "fecha_vto")
    @Temporal(TemporalType.DATE)
    private Date fechaVto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "importe")
    private Double importe;
    @Column(name = "cod_suc_fac")
    private String codSucFac;
    @Column(name = "cod_emi_fac")
    private String codEmiFac;
    @Column(name = "nro_docum")
    private Integer nroDocum;

    public CobranzaDet() {
    }

    public CobranzaDet(CobranzaDetPK cobranzaDetPK) {
        this.cobranzaDetPK = cobranzaDetPK;
    }

    public CobranzaDet(int nroRecibo, int linea) {
        this.cobranzaDetPK = new CobranzaDetPK(nroRecibo, linea);
    }

    public CobranzaDetPK getCobranzaDetPK() {
        return cobranzaDetPK;
    }

    public void setCobranzaDetPK(CobranzaDetPK cobranzaDetPK) {
        this.cobranzaDetPK = cobranzaDetPK;
    }

    public Integer getRefOperacion() {
        return refOperacion;
    }

    public void setRefOperacion(Integer refOperacion) {
        this.refOperacion = refOperacion;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Date getFechaVto() {
        return fechaVto;
    }

    public void setFechaVto(Date fechaVto) {
        this.fechaVto = fechaVto;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getCodSucFac() {
        return codSucFac;
    }

    public void setCodSucFac(String codSucFac) {
        this.codSucFac = codSucFac;
    }

    public String getCodEmiFac() {
        return codEmiFac;
    }

    public void setCodEmiFac(String codEmiFac) {
        this.codEmiFac = codEmiFac;
    }

    public Integer getNroDocum() {
        return nroDocum;
    }

    public void setNroDocum(Integer nroDocum) {
        this.nroDocum = nroDocum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cobranzaDetPK != null ? cobranzaDetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CobranzaDet)) {
            return false;
        }
        CobranzaDet other = (CobranzaDet) object;
        if ((this.cobranzaDetPK == null && other.cobranzaDetPK != null) || (this.cobranzaDetPK != null && !this.cobranzaDetPK.equals(other.cobranzaDetPK))) {
            return false;
        }
        return true;
    }

}
