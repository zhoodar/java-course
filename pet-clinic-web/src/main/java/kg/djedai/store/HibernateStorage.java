package kg.djedai.store;

import kg.djedai.models.ClientModel;
import kg.djedai.models.Pet;
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
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            session.update(client);
        } finally {
            tn.commit();
            session.close();
        }
    }

    @Override
    public void deleteClient(String id) {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            session.delete(getClientById(id));
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
            final Query query = session.createQuery(
                    "from ClientModel as client inner join  client.pets as pet on " +
                            "client.id = pet.clientModel.id where client.nameClient =:clientName or pet.name=:name"
            );
            query.setString("clientName",clientName);
            query.setString("name",clientName);
            return query.list();
        }
        finally {
            tn.commit();
            session.close();
        }
    }

    @Override
    public List<ClientModel> findByContain(String partName) {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            final Query query = session.createQuery(
                    "from ClientModel as client where lower(client.nameClient) like lower(:partName)"
            );
            query.setString("partName",partName);
            return query.list();
        }
        finally {
            tn.commit();
            session.close();
        }
    }

    @Override
    public String generateId() {
        return RandomIdGenerator.getBase36(6);
    }

    @Override
    public void close() {
        this.factory.close();
    }

    @Override
    public void addPetToClient(Pet pet, String idClient) {
        pet.setClientModel(getClientById(idClient));
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            session.save(pet);
        }finally {
            tn.commit();
            session.close();
        }

    }

    @Override
    public List<Pet> getPetCurrentClient(String idCurrentClient) {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
            final Query query = session.createQuery("from Pet as pet where pet.clientModel =:id");
            query.setString("id",idCurrentClient);
            return query.list();
        } finally {
            tn.commit();
            session.close();
        }
    }

    @Override
    public void deletePetCurrentClient(String idCurrentClient, String petName) {
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try {
           final Query query = session.createQuery("delete Pet as pet where pet.clientModel=:id and pet.name=:name");
            query.setString("id",idCurrentClient);
            query.setString("name",petName);
            query.executeUpdate();
        } finally {
            tn.commit();
            session.close();
        }
    }
}
