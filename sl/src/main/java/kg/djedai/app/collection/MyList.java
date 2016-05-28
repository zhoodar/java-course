package kg.djedai.app.collection;

import java.util.Iterator;

/**
 * Interface
 * @author Zhoodar on 26.04.2016.
 */
public interface MyList<T> {
    /**
     * Возврашает iterator
     * @return iterator над елементами в листе
     */
    public Iterator<T> iterator();
}
