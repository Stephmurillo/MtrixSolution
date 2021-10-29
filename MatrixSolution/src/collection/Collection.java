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
        return toString(false);
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
                r.append("\t");
            }
            r.append(i.next());
            if (i.hasNext()) {
                r.append(", ");
            }
        }
        r.append("]\n");
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
