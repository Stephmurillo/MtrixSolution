
package collection;

/**
 *
 * @author muril
 */
public class Casilla {

    public Casilla(int fila, int columna, int valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Casilla{" + "fila=" + fila + ", columna=" + columna + ", valor=" + valor + '}';
    }
    
    
    public int fila;
    public int columna;
    public int valor;
}

