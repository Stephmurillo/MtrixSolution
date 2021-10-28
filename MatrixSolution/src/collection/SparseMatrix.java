
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
    
    public SparseMatrix(int numRows, int numColumns, T objItem) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        List<T> tempRowList = new List<>();
        List<T> tempColunmList = new List<>();
        for(int i = 0; i < this.numColumns; i++){
            tempRowList.add(objItem);
        }
        for(int i = 0; i < this.numRows; i++){
            tempColunmList.add(objItem);
        }
        for(int i = 0; i < this.numRows; i++){
            addRow(tempRowList);
        }
        for(int i = 0; i < this.numColumns; i++){
            addColumn(tempColunmList);
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }
    
    public T getObjItem() {
        return objItem.getInfo();
    }
    
//    public T get(int f) {
//        T r = null;
//        List<T> auxRow = this.rows.get(f);
//        if ((0 <= f) && (f < this.rows.size())) {
//            while (auxRow != null) {
//                if (auxRow.getFirst().getInfo().toString() == f) {
//                } else {
//
//                }
//            }
//        }
//        return null;
//    }
    
//    public T getBoxItem(int numRow, int numColumn) {
//        List<T> aux = rows.get(numRow);
//        if(aux.getObject(numColumn) != null){
//            return aux.getObject(numColumn);
//        }
//        return null;
//    }
    
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
        return (String) objItem.getInfo();
    }
    
    public List<T> getFila(int i) {
        List<T> list = new List<T>();
            list = rows.get(i);
        return list;
    }
        
    public SparseMatrix<T> transpose() {
        SparseMatrix<T> newMatrix = new SparseMatrix<T>(this.getNumColumns(), this.getNumRows());
        try {
            for (int i = 0; i <= this.getNumRows(); i++) {
                List<T> fila = this.getFila(i);
                newMatrix.addColumn(fila);
            }

        } catch (Exception Ex) {

        }
        return newMatrix;
    }
    
    public List<List<T>> rows = new List();
    public List<List<T>> columns = new List();
    public Node<T> objItem = new Node();
    public int numRows;
    public int numColumns;
    
}