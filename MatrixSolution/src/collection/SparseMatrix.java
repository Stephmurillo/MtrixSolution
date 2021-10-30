
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
        this.emptyObj = objItem;
        this.numRows = numRows;
        this.numColumns = numColumns;
        List<T> tempRowList = new List<>();
        
        for(int i = 0; i < this.numColumns; i++){
            tempRowList.add(objItem);
        }
        for(int i = 0; i < this.numRows; i++){
            this.addRow(tempRowList);
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
    
    public void addT(int numRow, int numColumn, Node<T> objItem){
        this.rows.get(numRow).add(objItem, numColumn);
    }
    
    public T getT(int r, int c) {
        T obj = null;
        List<T> fila = this.rows.get(r); // extrae una fila especifica de la matriz
        obj = fila.get(c); // obtiene el elemento con la posicion en la columna especifica
        return obj;
    }
    
    public void setT(int r, int c, T obj) {
        if(r < this.getNumRows() && c < this.getNumColumns()){//evalua que la posicion a cambiar este dentro de las dimensiones de la matriz
            List<T> fila = new List();
            
            for(int i = 0; i < this.numColumns; i++) {
                if (i == c) {
                    fila.add(obj);//inserta el valor recibido en la fila cuando esta en lña posicion de columna
                } else if (this.getRows().get(r).get(i) != this.emptyObj) { //si el objeto no es empty(clase representa vacio)
                    fila.add(this.getRows().get(r).get(i)); //agrega el objeto de la fila r en la posicion de columna i
                }else {
                    fila.add(this.emptyObj);//agrega a fila un elemento empty(clase representa vacio)
                }
            }

            this.getRows().remove(r);//remueve de las filas de la matriz la fila especifica donde se va a cambiar el valor
            this.getRows().add(fila, r); //agrega nuevamente en esa posicion la nueva fila donde se cambiado el objeto debido

            System.out.println("----");
        }
    }
    
    @Override
    public String toString() {
        return "SparseMatrix{\n" + rows + "\nnumRows: " + numRows + ", numColumns: " + numColumns + "}";
    }
    
    public List<List<T>> getRows() {
        return rows;
    }
    
    public SparseMatrix<T> transpose(){
        SparseMatrix<T> newMatrix = new SparseMatrix(this.getNumColumns(), this.getNumColumns(),this.emptyObj); // crea una nueva matriz con las dimensiones trspuestas
        
            for(int i = 0; i < this.getNumColumns(); i++) {
                for(int j = 0; j < this.getNumRows(); j++) {
                        newMatrix.setT(i,j,this.getRows().get(j).get(i));//obtiene la lista de filas en una posicion y el objeto ubicaado e la posicion de la columna correspodiente
                }   //asigna a la nueva matriz creada el valor obtenido 
            }
        return newMatrix;
        
    }
    
    public SparseMatrix<T> fitMatrix(SparseMatrix<T> m, int r, int c, T empty){
        SparseMatrix<T> newMatrix = new SparseMatrix(r, c);
        int count = 0;
        
        for(int i = 0; i < r; i++){
            List<T> tempRowList = new List<>();
            List<T> specificRow = m.rows.get(i);//extrae la lista especificada por parametro
            
            if(count == r){
                break;
            }
            
            if (specificRow != null && specificRow.size() > 0) {  // evalua que la fila especifica tenga al menos un objeto
                
                for(int j = 0; j < c; j++){
                    if(specificRow.get(j) != null){
                        tempRowList.add(specificRow.get(j));//añade a la nueva lista los objetos de la fila
                    } else {
                        tempRowList.add(empty);// si hay espacios nulos inserta un objeto empty(clase representa vacio)
                    }
                }

                newMatrix.addRow(tempRowList);// añade la lista resultante como una fila de la nueva matriz
                count++;
            } else {
                for(int k = 0; k < c; k++) {
                    tempRowList.add(empty);//si la fila especifica es nula la llena con objetos empty(clase representa vacio)
                }
                newMatrix.addRow(tempRowList);
                count++;
            }
        }
        return newMatrix;
    }
    

    public boolean Equals(SparseMatrix<T> other){
        boolean flag = false;
        for (int i = 0; i < this.getNumColumns(); i++) {
            for (int j = 0; j < this.getNumRows(); j++) {
               if(this.getNumRows() == other.getNumRows() && this.getNumColumns() == other.getNumColumns()){
                    if(this.getRows().get(i).get(j).equals(other.getRows().get(i).get(j))){
                        flag = true;
                    }
                    else{
                       return false;
                    }
               }
               else{
                   flag = false;
               }
            } 
        }
        return flag;  
    }

    public SparseMatrix<T> add(SparseMatrix<T> m){
    
    return null;
    }
    
    public List<List<T>> rows = new List();
    public Node<T> objItem = new Node();
    public int numRows;
    public int numColumns;
    public T emptyObj;
    
}