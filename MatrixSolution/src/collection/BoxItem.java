
package collection;

/**
 *
 * @author muril
 */
public class BoxItem {

    public BoxItem(int row, int column, int valor) {
        this.row = row;
        this.column = column;
        this.valor = valor;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "{"+ valor + '}';
    }
    
    
    public int row;
    public int column;
    public int valor;
}

