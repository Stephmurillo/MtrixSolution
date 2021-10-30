
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

    public SparseMatrix(int numRows, int numColumns, T objItem) {
        this.emptyObj = objItem;
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
            this.addRow(tempRowList);
        }
        for(int i = 0; i < this.numColumns; i++){
            this.addColumn(tempColunmList);
        }
    }
    
    public SparseMatrix(int numRows, int numColumns) {
        Integer val = 0;
        T obj = (T) val;
        this.numRows = numRows;
        this.numColumns = numColumns;
        List<T> tempRowList = new List<>();
        List<T> tempColunmList = new List<>();
        
        for(int i = 0; i < this.numColumns; i++){
            tempRowList.add(obj);
        }
        for(int i = 0; i < this.numRows; i++){
            tempColunmList.add(obj);
        }
        for(int i = 0; i < this.numRows; i++){
            this.addRow(tempRowList);
        }
        for(int i = 0; i < this.numColumns; i++){
            this.addColumn(tempColunmList);
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }
    
    void setNumRows(int r) {
        numRows = r;
    }

    void setNumColumns(int c) {
        numColumns = c;
    }

    public void addRow(List<T> row){
        this.rows.add(row);
    }
    
    public void addColumn(List<T> row){
        this.columns.add(row);
    }
    
    public void addT(int numRow, int numColumn, Node<T> objItem){
        this.columns.get(numColumn).add(objItem, numColumn);
        this.rows.get(numRow).add(objItem, numRow);
    }
    
    public T getT(int r, int c) {
        T obj = null;
        List<T> fila = this.rows.get(r);
        obj = fila.get(c);
        return obj;
    }
    
    public void setT(int r, int c, T obj) {
        if(r < this.getNumRows() && c < this.getNumColumns()){
            List<T> fila = new List();
            
            for(int i = 0; i < this.numColumns; i++) {
                if (i == c) {
                    fila.add(obj);
                } else if (this.getRows().get(r).get(i) != this.emptyObj) {
                    fila.add(this.getRows().get(r).get(i));
                }else {
                    fila.add(this.emptyObj);
                }
            }

            this.getRows().remove(r);
            this.getRows().add(fila, r);

            System.out.println("----");
        }
    }
    
    @Override
    public String toString() {
        return "SparseMatrix{\n" + rows + "\nnumRows: " + numRows + ", numColumns: " + numColumns + "}";
        /*(String) objItem.getInfo();*/
    }
        
    public SparseMatrix<T> splice(int f1, int f2, int c1, int c2) {
        int rows = (f2 - f1 + 1);
        int columns = (c2 - c1 + 1);
        SparseMatrix<T> newMatrix = new SparseMatrix<T>(rows, columns);
        try {
            int row = 0;
            for (int i = f1; i <= f2; i++) {
                int column = 0;
                for (int j = c1; j <= c2; j++) {
                    T obj = this.getT(i, j);
                    newMatrix.setT(row, column, obj);
                    column++;
                }
                row++;
            }
        } catch (Exception ex) {
        }
        return newMatrix;
    }
    
    public SparseMatrix<T> fitMatrix(SparseMatrix<T> m, int r, int c, T empty){
        SparseMatrix<T> newMatrix = new SparseMatrix(r, c);
        int count = 0;
        
        for(int i = 0; i < r; i++){
            List<T> tempRowList = new List<>();
            List<T> specificRow = m.rows.get(i);
            
            if(count == r){
                break;
            }
            
            if (specificRow != null && specificRow.size() > 0) {
                
                for(int j = 0; j < c; j++){
                    if(specificRow.get(j) != null){
                        tempRowList.add(specificRow.get(j));
                    } else {
                        tempRowList.add(empty);
                    }
                }

                newMatrix.addRow(tempRowList);
                count++;
            } else {
                for(int k = 0; k < c; k++) {
                    tempRowList.add(empty);
                }
                newMatrix.addRow(tempRowList);
                count++;
            }
        }
        return newMatrix;
    }

    public List<List<T>> getRows() {
        return rows;
    }

    public List<List<T>> getColumns() {
        return columns;
    }
    
    
    public List<List<T>> rows = new List();
    public List<List<T>> columns = new List();
    public Node<T> objItem = new Node();
    public int numRows;
    public int numColumns;
    public T emptyObj;
    
}