package kg.djedai.store;

import kg.djedai.models.User;

import java.util.Collection;

/**
 * @author Zhoodar
 * @since 03.07.2016.
 */
public class UserStorage implements DAO<User> {
    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public User findBy(String toSearch) {
        return null;
    }

    @Override
    public void close() {

    }
}
