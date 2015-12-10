
/*
* Clase generica para concentrar metodos de manipulacion de valores numericos.
*/
package py.com.ventu.util;

/**
 * @author carlitox
 */
public class NumeroUtil {
    
    /*
        Metodo para devolver un valor numerico redondeado con X decimales
        @param: importe = valor a ser redondeado
        @param: decimales = cantidad de digitos de a ser redondeados
    */
    
    public static Double redondear(Double importe, int decimales) {
        return Math.round(importe * Math.pow(10, decimales)) / Math.pow(10, decimales);
    }
    
}
