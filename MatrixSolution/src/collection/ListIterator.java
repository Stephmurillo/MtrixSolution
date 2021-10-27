package collection;

import java.util.Iterator;

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

public class ListIterator<T> implements Iterator<T> {

    public ListIterator(Node<T> first) {
        this.first = first;
        this.current = first;
    }

    public void restart() {
        current = first;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        T r = null;
        if (hasNext()) {
            r = current.getInfo();
            current = current.getRight();
        }
        return r;
    }

    private final Node<T> first;
    private Node<T> current;
}
