package kg.djedai.store;

import kg.djedai.models.ClientModel;
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
        final String id = "55XXCCHH";
        storage.addClient(new ClientModel(id, "hibernate"));
        final ClientModel client = storage.getClientById(id);
        assertEquals(id, client.getId());
        assertEquals(1, storage.findByFullName("hibernate").size());
        storage.deleteClient(id);
        assertNull(storage.getClientById(id));
        storage.close();
    }

}