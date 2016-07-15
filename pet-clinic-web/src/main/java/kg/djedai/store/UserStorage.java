package kg.djedai.store;

import kg.djedai.models.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private HibernateTemplate template;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
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
    public User findBy(String toSearch, String password){
        List user = this.template.find("from User as user where user.login=? and user.password=?",toSearch,password);
        if(!user.isEmpty())
            return (User) user.iterator().next();
        return null;
    }

    @Override
    public void close(){
    }
}
