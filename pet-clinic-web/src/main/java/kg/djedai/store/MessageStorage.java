package kg.djedai.store;

import kg.djedai.models.Message;

import java.util.Collection;

/**
 * @author Zhoodar
 * @since 03.07.2016.
 */
public class MessageStorage implements DAO<Message> {
    @Override
    public Collection<Message> getAll() {
        return null;
    }

    @Override
    public void create(Message message) {

    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Message read(int id) {
        return null;
    }

    @Override
    public Message findBy(String toSearch) {
        return null;
    }

    @Override
    public void close() {

    }
}
