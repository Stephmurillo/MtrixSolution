
package collection;

/**
*
* (c) 2021
* @author Yoselin Rojas, Cinthya Murillo, Sebastián Cabezas
* @version 1.0.0 2021-10-24
*
* -----------------------------------------------
* EIF207 Estructuras de Datos
* 2do ciclo 2021
* Proyecto 1
*
* 207700499 Rojas Fuentes, Yoselin - Grupo 04
* 305260682 Murillo Hidalgo, Cinthya - Grupo 05
* 402260762 Cabezas Madrigal, Sebastián  - Grupo 4
* -----------------------------------------------
}
 * @param <T>
**/
public class SparseMatrix<T> {

    public SparseMatrix(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        
    }
    
    public void addRow(List<T> row){
        this.rows.add(row);
    }
    
    public void addColumn(List<T> row){
        this.columns.add(row);
    }
    
    public void addBoxItem(int numRow, int numColumn, Node<T> objItem){
        this.columns.get(numColumn).add(objItem, numColumn);
        this.rows.get(numRow).add(objItem, numRow);
    }
    
    @Override
    public String toString() {
        return "SparseMatrix{" + "\nrows=" + rows + ", \ncolumns=" + columns + ", numRows=" + numRows + ", numColumns=" + numColumns + '}';
    }
    
    public List<List<T>> rows = new List();
    public List<List<T>> columns = new List();
    public int numRows;
    public int numColumns;
}