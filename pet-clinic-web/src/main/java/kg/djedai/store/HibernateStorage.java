package kg.djedai.store;

import kg.djedai.app.clinic.Pet;
import kg.djedai.models.ClientModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Collection;
import java.util.List;

/**
 * Реализация Storage  используя Hibernate
 * @author Zhoodar
 * @since 23.06.2016.
 */
public class HibernateStorage implements Storage {

    private final SessionFactory factory;

    public HibernateStorage(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Collection<ClientModel> getClients() {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try{
            return session.createQuery("from ClientModel ").list();
        } finally {
            tn.commit();
            session.close();
        }
    }

    @Override
    public void addClient(ClientModel client) {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            session.save(client);
        } finally {
            tn.commit();
            session.close();
        }
    }

    @Override
    public void editClient(ClientModel client) {

    }

    @Override
    public void deleteClient(String id) {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            session.delete(new ClientModel(id,null));
        } finally {
            tn.commit();
            session.close();
        }

    }

    @Override
    public ClientModel getClientById(String id) {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            return session.get(ClientModel.class, id);
        } finally {
            tn.commit();
            session.close();
        }
    }

    @Override
    public ClientModel getLastClient() {
        return null;
    }

    @Override
    public List<ClientModel> findByFullName(String clientName) {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            final Query query = session.createQuery("from ClientModel  as client where client.nameClient =:clientName");
            query.setString("clientName",clientName);
            return query.list();
        }
        finally {
            tn.commit();
            session.close();
        }
    }

    @Override
    public List<ClientModel> findByContain(String partName) {
        return null;
    }

    @Override
    public String generateId() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void addPetToClient(int type, String namePet, String idClient) {

    }

    @Override
    public List<Pet> getPetCurrentClient(String idCurrentClient) {
        return null;
    }

    @Override
    public void deletePetCurrentClient(String idCurrentClient, String petName) {

    }
}
