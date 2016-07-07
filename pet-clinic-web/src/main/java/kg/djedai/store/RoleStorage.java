package kg.djedai.store;

import kg.djedai.models.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;

/**
 * @author Zhoodar
 * @since 03.07.2016.
 */
@Repository
public class RoleStorage implements RoleDAO {

    private final HibernateTemplate template;
    @Autowired
    public RoleStorage(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<Role> getAll() {
        return (List<Role>) this.template.find("from Role");
    }

    @Transactional
    @Override
    public void create(Role role) {
        this.template.save(role);
    }

    @Transactional
    @Override
    public void update(Role role) {
        this.template.update(role);
    }

    @Transactional
    @Override
    public void delete(int id) {
        this.template.delete(read(id));
    }

    @Override
    public Role read(int id) {
        return this.template.get(Role.class, id);
    }

    @Override
    public Role findBy(String toSearch) {
        return (Role) this.template.find("from Role as role where role.name=?",toSearch).iterator().next();
    }

    @Override
    public void close(){
    }
}
