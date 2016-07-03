package kg.djedai.store;

import java.util.Collection;

/**
 * Интерфейс предоставляющий базовую реализацию CRUD операций
 * @author Zhoodar
 * @since 03.07.2016.
 */
public interface DAO<T> {

    /**
     *
     * @return
     */
    Collection<T> getAll();

    /**
     *
     * @param t
     */
    void create(final T t);

    /**
     *
     * @param id
     * @return
     */
    T read(final int id);

    /**
     *
     * @param t
     */
    void update(final T t);

    /**
     *
     * @param id
     */
    void delete(final int id);


    /**
     *
     * @param toSearch
     * @return
     */
    T findBy(final String toSearch);

    /**
     *
     */
    void close();


}
