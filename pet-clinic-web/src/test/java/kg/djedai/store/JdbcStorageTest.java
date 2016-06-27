package kg.djedai.store;

import kg.djedai.models.ClientModel;
import kg.djedai.models.Dog;
import kg.djedai.models.Pet;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Zhoodar
 * @since 12.06.2016.
 */
public class JdbcStorageTest {



    @Test
    public void testCreate(){
        final JdbcStorage storage = new JdbcStorage();
        String nameClient = "John";
        String id = storage.generateId();
        Pet pet = new Dog("labrador");

        storage.addClient(new ClientModel(id,nameClient));
        storage.addPetToClient(pet, id);

        assertFalse(storage.findByFullName(nameClient).isEmpty());
        assertFalse(storage.findByFullName("labrador").isEmpty());
        storage.close();
    }

    @Test
    public void testEdit(){
        final JdbcStorage storage = new JdbcStorage();
        String nameClient = "TestClient";
        String id = storage.generateId();
        String editedName="EditedClient";
        storage.addClient(new ClientModel(id,nameClient));
        assertFalse(storage.getClientById(id).getNameClient().equals(editedName));
        storage.editClient(new ClientModel(id,editedName));
        assertTrue(storage.getClientById(id).getNameClient().equals(editedName));
        storage.close();
    }

    @Test
    public void testDelete(){
        final JdbcStorage storage = new JdbcStorage();
        String nameClient = "Client";
        String id = storage.generateId();
        String namePet = "reks";
        Pet pet = new Dog(namePet);

        storage.addClient(new ClientModel(id,nameClient));
        storage.addPetToClient(pet,id);

        assertFalse(storage.findByFullName(nameClient).isEmpty());
        assertFalse(storage.findByFullName(namePet).isEmpty());

        storage.deletePetCurrentClient(id,"reks");
        storage.deleteClient(id);
        assertTrue(storage.getPetCurrentClient(id).isEmpty());
        assertTrue(storage.getClientById(id) == null);
        storage.close();

    }
}