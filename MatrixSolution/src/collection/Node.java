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
 * @param <T>
*
*
*/
public class Node<T> {

    public Node(T info, Node<T> right, Node<T> down) {
        this.info = info;
        this.right = right;
        this.down = down;
    }
    
    public Node(T info) {
        this(info, null, null);
    }

    public Node() { }
    
    public void setInfo(T info) {
        this.info = info;
    }
    
    public T getInfo() {
        return info;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> getDown() {
        return down;
    }

    public void setDown(Node<T> down) {
        this.down = down;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    private T info;
    private Node<T> right;
    private Node<T> down;
}
