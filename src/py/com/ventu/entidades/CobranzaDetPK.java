
package py.com.ventu.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author cpatino
 */
@Embeddable
public class CobranzaDetPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "nro_recibo")
    private int nroRecibo; // nro. trans de la cabecera
    @Basic(optional = false)
    @Column(name = "linea")
    private int linea;

    public CobranzaDetPK() {
    }

    public CobranzaDetPK(int nroRecibo, int linea) {
        this.nroRecibo = nroRecibo;
        this.linea = linea;
    }

    public int getNroRecibo() {
        return nroRecibo;
    }

    public void setNroRecibo(int nroRecibo) {
        this.nroRecibo = nroRecibo;
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
        hash += (int) nroRecibo;
        hash += (int) linea;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CobranzaDetPK)) {
            return false;
        }
        CobranzaDetPK other = (CobranzaDetPK) object;
        if (this.nroRecibo != other.nroRecibo) {
            return false;
        }
        if (this.linea != other.linea) {
            return false;
        }
        return true;
    }

}
