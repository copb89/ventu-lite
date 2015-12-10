
package py.com.ventu.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author cpatino
 */
@Entity
@Table(name = "factura_venta_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaVentaDet.findAll", query = "SELECT f FROM FacturaVentaDet f"),
    @NamedQuery(name = "FacturaVentaDet.findByTicket", query = "SELECT f FROM FacturaVentaDet f WHERE f.facturaVentaDetPK.ticket = :ticket")})
public class FacturaVentaDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FacturaVentaDetPK facturaVentaDetPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "precio_unit")
    private Double precioUnit;
    @Column(name = "factor")
    private Double factor;
    @Column(name = "proc_dscto")
    private Double procDscto;
    @Column(name = "imp_dscto")
    private Double impDscto;
    @Column(name = "subototal")
    private Double subototal;
    @JoinColumn(name = "producto", referencedColumnName = "id")
    @ManyToOne
    private Producto producto;

    public FacturaVentaDet() {
    }

    public FacturaVentaDet(FacturaVentaDetPK facturaVentaDetPK) {
        this.facturaVentaDetPK = facturaVentaDetPK;
    }

    public FacturaVentaDet(int ticket, int linea) {
        this.facturaVentaDetPK = new FacturaVentaDetPK(ticket, linea);
    }

    public FacturaVentaDetPK getFacturaVentaDetPK() {
        return facturaVentaDetPK;
    }

    public void setFacturaVentaDetPK(FacturaVentaDetPK facturaVentaDetPK) {
        this.facturaVentaDetPK = facturaVentaDetPK;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(Double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public Double getProcDscto() {
        return procDscto;
    }

    public void setProcDscto(Double procDscto) {
        this.procDscto = procDscto;
    }

    public Double getImpDscto() {
        return impDscto;
    }

    public void setImpDscto(Double impDscto) {
        this.impDscto = impDscto;
    }

    public Double getSubototal() {
        return subototal;
    }

    public void setSubototal(Double subototal) {
        this.subototal = subototal;
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaVentaDetPK != null ? facturaVentaDetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaVentaDet)) {
            return false;
        }
        FacturaVentaDet other = (FacturaVentaDet) object;
        if ((this.facturaVentaDetPK == null && other.facturaVentaDetPK != null) || (this.facturaVentaDetPK != null && !this.facturaVentaDetPK.equals(other.facturaVentaDetPK))) {
            return false;
        }
        return true;
    }

}
