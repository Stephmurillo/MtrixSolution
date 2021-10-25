package collection;

import java.util.Iterator;

/**
 *
 * (c) 2021
 *
 * @author Georges Alfaro S.
 * @version 1.0.0 2021-08-21
 *
 */
public abstract class Collection<T> implements Iterable<T> {

    public Collection(String name) {
        this.n = 0;
        this.name = name;
    }

    public Collection() {
        this(null);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    public int size() {
        return n;
    }

    public abstract void add(T obj);

    public abstract void add(T obj, int pos);

    public void addAll(Collection<T> c) {
        Iterator<T> i = c.iterator();
        while (i.hasNext()) {
            add(i.next());
        }
    }

    public abstract T remove();

    public abstract T remove(int pos);

    public abstract T remove(T obj);

    public int getPosition(T obj) {
        int p = -1;
        if (obj != null) {
            p = 0;
            Iterator<T> i = iterator();
            boolean found = false;
            while (i.hasNext() && !found) {
                if (!(found = (i.next().equals(obj)))) {
                    p++;
                }
            }
            if (!found) {
                p = -1;
            }
        }
        return p;
    }

    public abstract T get(int pos);

    public abstract Iterator<T> iterator();

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean f) {
        StringBuilder r = new StringBuilder();
        if (f) {
            r.append(String.format("%s:", getClass().getSimpleName()));
        }
        r.append("[");
        Iterator<T> i = iterator();
        while (i.hasNext()) {
            if (f) {
                r.append("\n\t");
            }
            r.append(i.next());
            if (i.hasNext()) {
                r.append(", ");
            }
        }
        if (f) {
            r.append("\n");
        }
        r.append("]");
        return r.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected int n;
    private String name;
}
