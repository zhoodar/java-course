package kg.djedai.store;

import kg.djedai.models.Message;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author Zhoodar
 * @since 03.07.2016.
 */
@Repository
public class MessageStorage implements DAO<Message> {

    private HibernateTemplate template;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    @Override
    public Collection<Message> getAll() {
        return (List<Message>) this.template.find("from Message");
    }

    @Transactional
    @Override
    public void create(Message msg) {
        this.template.save(msg);
    }

    @Transactional
    @Override
    public void update(Message msg) {
        this.template.update(msg);
    }

    @Transactional
    @Override
    public void delete(int id) {
        this.template.delete(read(id));
    }

    @Override
    public Message read(int id) {
        return this.template.get(Message.class, id);
    }

    @Override
    public Message findBy(String toSearch) {
        return null;
    }

    @Override
    public void close(){
    }
}
