package py.com.ventu.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
@Table(name = "factura_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaVenta.findAll", query = "SELECT f FROM FacturaVenta f"),
    @NamedQuery(name = "FacturaVenta.findByTicket", query = "SELECT f FROM FacturaVenta f WHERE f.ticket = :ticket")})
public class FacturaVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nro_ticket") // nro. de ticket de venta y nro de operacion
    private Integer ticket;
    
    @Column(name = "condicion_venta")
    private Integer condicionVenta; // contado 1 - credito 2
    
    @Column(name = "estado")
    private Integer estado; // 1 Pendiente - 2 Cancelado - 3 Anulado
    
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVta; // fecha de la operacion
    
    @Column(name = "importe_total")
    private Double importeTotal;
    
    @Column(name = "total_dscto")
    private Double totalDscto;
    
    @Column(name = "vencimiento") // vto. de la factura venta
    @Temporal(TemporalType.DATE)
    private Date vencimiento;
    
    @Column(name = "vendedor")
    private String vendedor;
    
    @Column(name = "usuario_mod")
    private String usuarioMod;
    
    @Column(name = "usuario_anu")
    private String usuarioAnu;
    
    @Column(name = "fecha_anu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnu; // fecha de anulacion venta
   
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne
    private Cliente cliente;
   
    @JoinColumn(name = "caja", referencedColumnName = "id")
    @ManyToOne
    private Caja caja;

    public FacturaVenta() {
    }

    public Integer getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(Integer condicionVenta) {
        this.condicionVenta = condicionVenta;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Double getTotalDscto() {
        return totalDscto;
    }

    public void setTotalDscto(Double totalDscto) {
        this.totalDscto = totalDscto;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Date getFechaVta() {
        return fechaVta;
    }

    public void setFechaVta(Date fechaVta) {
        this.fechaVta = fechaVta;
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

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getUsuarioAnu() {
        return usuarioAnu;
    }

    public void setUsuarioAnu(String usuarioAnu) {
        this.usuarioAnu = usuarioAnu;
    }
    
    public Date getFechaAnu() {
        return fechaAnu;
    }

    public void setFechaAnu(Date fechaAnu) {
        this.fechaAnu = fechaAnu;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.ticket);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FacturaVenta other = (FacturaVenta) obj;
        if (!Objects.equals(this.ticket, other.ticket)) {
            return false;
        }
        return true;
    }

}
