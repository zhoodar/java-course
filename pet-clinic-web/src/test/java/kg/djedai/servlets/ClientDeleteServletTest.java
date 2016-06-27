
package kg.djedai.servlets;

import kg.djedai.app.clinic.Cat;
import kg.djedai.models.ClientModel;
import kg.djedai.models.Dog;
import kg.djedai.models.Pet;
import kg.djedai.store.ClientCache;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;


/*
 * @author Zhoodar
 * @since 06.06.2016.
 */

public class ClientDeleteServletTest extends Mockito{


    final ClientCache clinic = ClientCache.getInstance();

    @Test
    public void testClientDelete() throws ServletException, IOException {
        this.clinic.addClient(new ClientModel("1","test_Client"));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getContextPath()).thenReturn("/pcw/view");

        assertEquals(1,this.clinic.findByFullName("test_Client").size());

        new ClientDeleteServlet().doGet(request,response);

        verify(request,atLeast(1)).getParameter("id");
        verify(request,atLeast(1)).getContextPath();

    }
    @Test
    public void testPetDelete()throws ServletException,IOException {
        this.clinic.addClient(new ClientModel("2","testCl"));
        Pet pet = new Dog("Bobik");
        this.clinic.addPetToClient(pet,"2");

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("2");
        when(request.getParameter("petName")).thenReturn("Bobik");
        when(request.getContextPath()).thenReturn("/pcw/");

        assertEquals(1,this.clinic.getPetCurrentClient("2").size());
        new ClientDeleteServlet().doPost(request,response);

        verify(request,atLeast(1)).getParameter("id");
        verify(request,atLeast(1)).getParameter("petName");
        verify(request,atLeast(1)).getContextPath();
        assertTrue(this.clinic.getPetCurrentClient("2").isEmpty());

    }


}
