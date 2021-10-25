package collection;

import java.util.Iterator;


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
