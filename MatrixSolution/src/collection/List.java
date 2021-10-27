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

public class List<T> extends Collection<T> {

    public List(String name) {
        super(name);
        this.n = 0;
        this.first = this.last = null;
    }

    public List() {
        this(null);
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void add(T obj) {
        if (obj != null) {
            Node<T> tmp = new Node<>(obj);
            if (first == null) {
                first = last = tmp;
            } else {
                last.setRight(tmp);
                last = tmp;
            }
            n++;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void add(T obj, int pos) {
        if (obj != null) {
            if ((pos < 0) || (n <= pos)) {
                add(obj);
            } else {
                if (pos == 0) {
                    first = new Node<>(obj, first, last);
                    if (first.getRight() == null) {
                        last = first;
                    }
                } else {
                    Node<T> cursor = first;
                    int k = 1;
                    while (k < pos) {
                        cursor = cursor.getRight();
                        k++;
                    }
                    cursor.setRight(new Node<>(obj, cursor.getRight(), cursor.getDown()));
                    if (cursor.getRight() == null) {
                        last = cursor;
                    }
                }
                n++;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public T remove() {
        T r = null;
        if (!isEmpty()) {
            if (first == last) {
                r = last.getInfo();
                first = last = null;
            } else {
                Node<T> cursor = first;
                while (cursor.getRight() != last) {
                    cursor = cursor.getRight();
                }
                r = cursor.getRight().getInfo();
                cursor.setRight(null);
                last = cursor;
            }
            n--;
        } else {
            throw new IndexOutOfBoundsException();
        }
        return r;
    }

    @Override
    public T remove(int pos) {
        T r = null;
        if (pos == (n - 1)) {
            r = remove();
        } else {
            if ((0 <= pos) && (pos < n)) {
                Node<T> tmp;
                if (pos == 0) {
                    r = first.getInfo();
                    tmp = first;
                    first = first.getRight();
                } else {
                    Node<T> cursor = first;
                    int k = 0;
                    while (k < (pos - 1)) {
                        cursor = cursor.getRight();
                        k++;
                    }
                    r = cursor.getRight().getInfo();
                    tmp = cursor.getRight();
                    cursor.setRight(cursor.getRight().getRight());
                    if (cursor.getRight() == null) {
                        last = cursor;
                    }
                }
                tmp.setRight(null);
                n--;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return r;
    }

    @Override
    public T remove(T obj) {
        T r = null;
        if (!isEmpty()) {
            if (first.getInfo().equals(obj)) {
                r = remove(0);
            } else {
                Node<T> cursor = first;
                boolean found = false;
                while ((cursor.getRight() != null) && !found) {
                    if (!(found = cursor.getRight().getInfo().equals(obj))) {
                        cursor = cursor.getRight();
                    }
                }
                if (found) {
                    r = cursor.getRight().getInfo();
                    Node<T> tmp = cursor.getRight();
                    cursor.setRight(cursor.getRight().getRight());
                    tmp.setRight(null);
                    if (cursor.getRight() == null) {
                        last = cursor;
                    }
                    n--;
                }
            }
        }
        return r;
    }

    @Override
    public T get(int pos) {
        T r = null;
        if ((0 <= pos) && (pos < size())) {

            Iterator<T> i = iterator();
            int k = 0;
            while (k < pos) {
                i.next();
                k++;
            }
            r = i.next(); 
        }
        return r;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(first);
    }

    private int n;
    private Node<T> first;

    public Node<T> getFirst() {
        return first;
    }
    private Node<T> last;

}
