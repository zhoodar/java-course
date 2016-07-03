package kg.djedai.store;

import kg.djedai.models.Role;

import java.util.Collection;

/**
 * @author Zhoodar
 * @since 03.07.2016.
 */
public class RoleStorage implements DAO<Role> {
    @Override
    public Collection<Role> getAll() {
        return null;
    }

    @Override
    public void create(Role role) {

    }

    @Override
    public void update(Role role) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Role read(int id) {
        return null;
    }

    @Override
    public Role findBy(String toSearch) {
        return null;
    }

    @Override
    public void close() {

    }
}
