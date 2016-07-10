package kg.djedai.store;

import kg.djedai.models.ClientModel;
import kg.djedai.models.Pet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Реализация Storage  используя Hibernate
 * @author Zhoodar
 * @since 23.06.2016.
 */
@Repository
public class HibernateStorage implements Storage {


    private HibernateTemplate template;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.template = new HibernateTemplate(sessionFactory);
    }

    @Override
    public Collection<ClientModel> getClients() {
        return (List<ClientModel>) this.template.find("from ClientModel");
    }

    @Transactional
    @Override
    public void addClient(final ClientModel client) {
        this.template.save(client);
    }

    @Transactional
    @Override
    public void editClient(final ClientModel client) {
       this.template.update(client);
    }

    @Transactional
    @Override
    public void deleteClient(String id) {
        this.template.delete(getClientById(id));
    }

    @Override
    public ClientModel getClientById(String id) {
        return this.template.get(ClientModel.class,id);
    }

    @Override
    public List<ClientModel> findByFullName(final String clientName) {
        return (List<ClientModel>) this.template.find(
                    "from ClientModel as client inner join  client.pets as pet on " +
                            "client.id = pet.clientModel.id where client.nameClient =? or pet.name=?",clientName,clientName);
    }

    @Override
    public List<ClientModel> findByContain(String partName) {
        return (List<ClientModel>) this.template.find(
                    "from ClientModel as client where lower(client.nameClient) like lower(?)", "%"+partName+"%");
    }

    @Override
    public String generateId() {
        return RandomIdGenerator.getBase36(6);
    }

    @Override
    public void close() {
    }

    @Transactional
    @Override
    public void addPetToClient(Pet pet, String idClient) {
        pet.setClientModel(getClientById(idClient));
        this.template.save(pet);
    }

    @Override
    public List<Pet> getPetCurrentClient(String idCurrentClient) {
        return (List<Pet>) this.template.find("from Pet as pet where pet.clientModel.id=?",idCurrentClient);
    }

    @Transactional
    @Override
    public void deletePetCurrentClient(String idCurrentClient, String petName) {
        template.bulkUpdate("delete Pet as pet where pet.name=? and pet.clientModel.id=?",petName,idCurrentClient);
    }
}
