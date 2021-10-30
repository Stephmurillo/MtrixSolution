
package collection;

/**
*
* (c) 2021
* @author Yoselin Rojas, Cinthya Murillo
* @version 1.0.0 2021-10-24
*
* -----------------------------------------------
* EIF207 Estructuras de Datos
* 2do ciclo 2021
* Proyecto 1
*
* 207700499 Rojas Fuentes, Yoselin - Grupo 04
* 305260682 Murillo Hidalgo, Cinthya - Grupo 05
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
        
        for(int i = 0; i < this.numColumns; i++){
            tempRowList.add(objItem);
        }
        for(int i = 0; i < this.numRows; i++){
            this.addRow(tempRowList);
        }
    }
    
    public SparseMatrix(int numRows, int numColumns) {
        Integer val = 0;
        T obj = (T) val;
        this.numRows = numRows;
        this.numColumns = numColumns;
        List<T> tempRowList = new List<>();
        
        for(int i = 0; i < this.numColumns; i++){
            tempRowList.add(obj);
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
        List<T> fila = this.rows.get(r); //retorna la fila específicada
        obj = fila.get(c); //retorna el objeto en la posición especificada de la fila
        if (obj == null) { //si el valor es nulo lo cambia por un cero
            Integer val = 0;
            obj = (T) val;
        }
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
        }
    }
    
    @Override
    public String toString() {
        return "SparseMatrix{\n" + rows + "\nnumRows: " + numRows + ", numColumns: " + numColumns + "}";
    }
    
    public SparseMatrix<T> add(SparseMatrix<T> m){
        SparseMatrix<T> newMatrix = null;
        try{
//        if(this.getNumRows() > m.getNumRows()){
//        //añada las filas que faltan a m
//        }
//        if(this.getNumRows() < m.getNumRows()){
//        //añada las filas que faltan a this
//        }
//        if(this.getNumColumns() > m.getNumColumns()){
//        //añada las columnas que faltan a m
//        }
//        if(this.getNumColumns() < m.getNumColumns()){
//        //añada las columnas que faltan a this
//        }
        
        newMatrix = new SparseMatrix<T>(this.getNumRows(), this.getNumColumns());
        if(this.getNumRows() == m.getNumRows() && this.getNumColumns() == m.getNumColumns()){
            for(int i = 0; i <= this.getNumColumns(); i++){
                for(int j = 1; j <= this.getNumColumns(); j++){
                    Integer val = Integer.parseInt(this.getT(i, j).toString()) + Integer.parseInt(m.getT(i, j).toString());
                    T obj = (T) val;
                    newMatrix.setT(i, j, obj);
                }
            }
        }
        }catch(Exception ex){}
        return newMatrix;
    }
    

    public SparseMatrix<T> transpose() {
        SparseMatrix<T> newMatrix = new SparseMatrix(this.getNumColumns(), this.getNumRows()); // crea una nueva matriz con las dimensiones trspuestas

        for (int i = 0; i < this.getNumColumns(); i++) {
            for (int j = 0; j < this.getNumRows(); j++) {
                newMatrix.setT(i, j, this.getRows().get(j).get(i));//obtiene la lista de filas en una posicion y el objeto ubicaado e la posicion de la columna correspodiente
            }   //asigna a la nueva matriz creada el valor obtenido 
        }
        return newMatrix;

    }
            
    public SparseMatrix<T> splice(int f1, int f2, int c1, int c2) {
        int rows = (f2 - f1 + 1); //calcula la cantidad de filas que tendrá la nueva matriz
        int columns = (c2 - c1 + 1); //calcula la cantidad de columnas que tendrá la nueva matriz
        SparseMatrix<T> newMatrix = new SparseMatrix<T>(rows, columns); 
        try {
            int row = 0; //variable para moverse por las filas
            for (int i = f1; i <= f2; i++) {
                int column = 0; //variable para moverse por las columnas
                for (int j = c1; j <= c2; j++) {
                    T obj = this.getT(i, j); //se extrae el objeto de la matrix original en cada posición
                    newMatrix.setT(row, column, obj); //se agrega el objeto a cada posición de la nueva matriz
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
            List<T> specificRow = m.rows.get(i);//extrae la lista especificada por parametro
            
            if(count == r){
                break;
            }
            
            if (specificRow != null && specificRow.size() > 0) {//evalua que la fila especifica tenga al menos un objeto
                
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
               if(this.getNumRows() == other.getNumRows() && this.getNumColumns() == other.getNumColumns()){ //si ambos objetos son iguales retorna true
                    if(this.getT(i, j).toString().compareTo(other.getT(i, j).toString()) == 0){                    
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

    public List<List<T>> getRows() {
        return rows;
    }
    
    public List<List<T>> rows = new List();
    public Node<T> objItem = new Node();
    public int numRows;
    public int numColumns;
    public T emptyObj;
    
}