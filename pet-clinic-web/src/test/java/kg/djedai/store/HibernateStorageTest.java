package kg.djedai.store;

import kg.djedai.models.Cat;
import kg.djedai.models.ClientModel;
import kg.djedai.models.Dog;
import kg.djedai.models.Pet;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Zhoodar
 * @since 23.06.2016.
 */
public class HibernateStorageTest {

    @Test
    public void testCreate(){
        final HibernateStorage storage = new HibernateStorage();
        final String id = storage.generateId();
        storage.addClient(new ClientModel(id, "Amanda"));
        final ClientModel client = storage.getClientById(id);
        assertEquals(id, client.getId());
        storage.deleteClient(id);
        assertNull(storage.getClientById(id));
        storage.close();
    }
    @Test
    public void testAddPet(){
        final HibernateStorage storage = new HibernateStorage();
        final String id = storage.generateId();
        storage.addClient(new ClientModel(id,"Hibernate"));
        Pet cat = new Cat("HibernateCat");
        Pet dog = new Dog("HibernateDog");
        storage.addPetToClient(cat,id);
        storage.addPetToClient(dog,id);

        assertEquals(id, storage.getClientById(id).getId());
        assertEquals(2,storage.getPetCurrentClient(id).size());

        storage.deletePetCurrentClient(id,"HibernateCat");
        assertEquals(1,storage.getPetCurrentClient(id).size());
        storage.deleteClient(id);
        assertNull(storage.getClientById(id));
        assertEquals(0,storage.getPetCurrentClient(id).size());
        storage.close();
    }

    @Test
    public void testSearch(){
        final HibernateStorage storage = new HibernateStorage();
        final String id = storage.generateId();
        String clientName = "Peter Cllk";
        String petName = "testPuppy";
        Pet cat = new Cat(petName);
        storage.addClient(new ClientModel(id,clientName));
        storage.addPetToClient(cat,id);

        assertEquals(1,storage.findByFullName(petName).size());
        assertEquals(1,storage.findByContain("llk").size());
        assertEquals(1,storage.findByFullName(clientName).size());

        storage.deleteClient(id);
        storage.close();
    }

    @Test
    public void testDelete(){
        final HibernateStorage storage = new HibernateStorage();
        final String iD = storage.generateId();
        String clientName = "Peter Ku";
        String petName = "Puppy";
        Pet cat = new Cat(petName);
        storage.addClient(new ClientModel(iD,clientName));
        storage.addPetToClient(cat,iD);
        String id = storage.getClients().iterator().next().getId();
        int size = storage.getClients().size();
        assertEquals(size,storage.getClients().size());
        storage.deleteClient(id);
        assertFalse(size==storage.getClients().size());
        storage.close();
    }

    @Test
    public void testEdit(){
        final HibernateStorage storage = new HibernateStorage();
        ClientModel client = storage.getClients().iterator().next();
        String id = client.getId();
        String editedName = "EditedName";
        assertFalse(client.getNameClient().equals(editedName));
        client.setNameClient(editedName);
        storage.editClient(client);
        assertTrue(storage.getClientById(id).getNameClient().equals(editedName));
        storage.close();
    }


}