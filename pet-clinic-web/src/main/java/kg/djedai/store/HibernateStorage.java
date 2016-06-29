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

    /**
     * Реализация шаблона проектирования Commander
     * @param <E>
     */
    public interface Commander<E>{
        E process(Session session);
    }

    private <E> E transaction(final Commander<E> commander){
        final Session session = factory.openSession();
        final Transaction tn = session.beginTransaction();
        try{
            return commander.process(session);
        } finally {
            tn.commit();
            session.close();
        }
    }
    @Override
    public Collection<ClientModel> getClients() {
        return transaction((Session session)->session.createQuery("from ClientModel ").list());
    }

    @Override
    public void addClient(final ClientModel client) {
        transaction(
                (Session session)-> {
                    session.save(client);
                    return null;
                }
        );

    }

    @Override
    public void editClient(final ClientModel client) {
        transaction(
                (Session session)-> {
                    session.update(client);
                    return null;
                }
        );
    }

    @Override
    public void deleteClient(String id) {
        transaction(
                (Session session)-> {
                    session.delete(getClientById(id));
                    return null;
                }
        );

    }

    @Override
    public ClientModel getClientById(String id) {
        return transaction((Session session)-> session.get(ClientModel.class, id));
    }

    @Override
    public List<ClientModel> findByFullName(final String clientName) {
        return transaction((Commander<List<ClientModel>>) session -> {
            final Query query = session.createQuery(
                    "from ClientModel as client inner join  client.pets as pet on " +
                            "client.id = pet.clientModel.id where client.nameClient =:clientName or pet.name=:name"
            );
            query.setString("clientName",clientName);
            query.setString("name",clientName);
            return query.list();
        });
    }

    @Override
    public List<ClientModel> findByContain(String partName) {
        return transaction((Commander<List<ClientModel>>) session -> {
            final Query query = session.createQuery(
                    "from ClientModel as client where lower(client.nameClient) like lower(:partName)"
            );
            query.setString("partName", "%"+partName+"%");
            return query.list();
        });
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
        transaction(
                (Session session) ->{
                    session.save(pet);
                    return null;
                }
        );
    }

    @Override
    public List<Pet> getPetCurrentClient(String idCurrentClient) {
        return transaction((Commander<List<Pet>>) session ->{
                    final Query query = session.createQuery("from Pet as pet where pet.clientModel =:id");
                    query.setString("id",idCurrentClient);
                    return query.list();
        });
    }

    @Override
    public void deletePetCurrentClient(String idCurrentClient, String petName) {
        transaction((Session session)->{
            final Query query = session.createQuery("delete Pet as pet where pet.name=:name and pet.clientModel=:id ");
            query.setString("name",petName);
            query.setString("id",idCurrentClient);
            query.executeUpdate();
            return null;
        });
    }
}
