package kg.djedai.app.clinic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicTest {

    Clinic clinic;
    @Before
    public void setUp(){
        clinic = new Clinic(2);
        clinic.addClient(0,new Client("Ross", new Cat("Richy")));
    }


    @Test
    public void testAddClient() throws Exception {

        assertEquals("Client: Ross, pet: Richy", clinic.toString(clinic.getClients()));

    }


    @Test
    public void testFindClientByPetName() throws Exception {

        assertEquals("Client: Ross, pet: Richy", clinic.toString(clinic.findClientByPetName("Richy")));

    }
    @Test
    public void testReNameClientName() throws Exception {
        assertEquals(true,clinic.reNameClientName("Ross","Richard"));

    }

    @Test
    public void testRemoveClientById() throws Exception {
        clinic.removeClientById("Ross");
        assertEquals("",clinic.toString(clinic.getClients()));
    }



}