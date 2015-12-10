
package py.com.ventu.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author cpatino
 */
@Embeddable
public class FacturaVentaDetPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "nro_ticket")
    private Integer ticket;
    @Basic(optional = false)
    @Column(name = "linea")
    private int linea;

    public FacturaVentaDetPK() {
    }

    public FacturaVentaDetPK(int ticket, int linea) {
        this.ticket = ticket;
        this.linea = linea;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ticket;
        hash += (int) linea;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaVentaDetPK)) {
            return false;
        }
        FacturaVentaDetPK other = (FacturaVentaDetPK) object;
        if (this.ticket != other.ticket) {
            return false;
        }
        if (this.linea != other.linea) {
            return false;
        }
        return true;
    }

}
