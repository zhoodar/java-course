package kg.djedai.store;

import kg.djedai.models.User;
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
public class UserStorage implements UserDAO {

    private final HibernateTemplate template;
    @Autowired
    public UserStorage(final HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public Collection<User> getAll() {
        return (List<User>) this.template.find("from User");
    }

    @Transactional
    @Override
    public void create(User user) {
        this.template.save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        this.template.update(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        this.template.delete(read(id));
    }

    @Override
    public User read(int id) {
        return this.template.get(User.class, id);
    }

    @Override
    public User findBy(String toSearch) {
        return (User) this.template.find("from User as user where user.login=?",toSearch).iterator().next();
    }

    @Override
    public void close(){
    }
}
