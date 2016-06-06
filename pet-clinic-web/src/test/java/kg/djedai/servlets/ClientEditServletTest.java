package kg.djedai.servlets;

import kg.djedai.app.clinic.Cat;
import kg.djedai.models.ClientModel;
import kg.djedai.store.ClientCache;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Zhoodar
 * @since 06.06.2016.
 */
public class ClientEditServletTest extends Mockito {

    final ClientCache clinic = ClientCache.getInstance();

    @Test
    public void testClientEdit()throws ServletException, IOException {
        this.clinic.addClient(new ClientModel(1,"test_Client",new Cat("testPet")));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);


        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("nameClient")).thenReturn("editedClient");
        when(request.getParameter("typePet")).thenReturn("dog");
        when(request.getParameter("namePet")).thenReturn("editedPet");
        when(request.getContextPath()).thenReturn("/pcw/");

        assertEquals(1,this.clinic.findByFullName("testPet").size());

        new ClientEditServlet().doPost(request,response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("typePet");
        verify(request, atLeast(1)).getParameter("nameClient");
        verify(request, atLeast(1)).getParameter("namePet");
        verify(request, atLeast(1)).getContextPath();

        assertNotSame(1,this.clinic.findByFullName("testPet").size());

    }


}