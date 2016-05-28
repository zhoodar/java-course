package kg.djedai.app.collection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicTest {

    Clinic clinic;
    @Before
    public void setUp(){
        clinic = new Clinic(2);
        clinic.addClient(new Client("Ross", new Cat("Richy")));
        clinic.addClient(new Client("Ross", new Cat("Richy")));
    }

    @Test
    public void testGetClients() throws Exception {

        System.out.println(clinic.getClients());
    }
}