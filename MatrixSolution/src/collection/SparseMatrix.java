
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

    public SparseMatrix(int numFilas/*, int numColumnas*/) {
        this.numFilas = numFilas;
        //this.numColumnas = numColumnas;
        
    }
    
    public void addFila(List<T> fila){
        this.filas.add(fila);
    }

    @Override
    public String toString() {
        return "SparseMatrix{" + "filas=" + filas + ", numFilas=" + numFilas + '}';
    }
   
    public List<List<T>> filas = new List();
   // public List<List<Integer>> columnas;
    public int numFilas;
    //public int numColumnas;
}