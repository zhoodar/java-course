package kg.djedai.servlets;

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

/*
 * @author Zhoodar
 * @since 06.06.2016.
 */

public class ClientEditServletTest extends Mockito {
    final ClientCache clinic = ClientCache.getInstance();

    @Test
    public void testClientEdit()throws ServletException, IOException {
        this.clinic.addClient(new ClientModel("1","test_Client"));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("nameClient")).thenReturn("editedClient");
        when(request.getParameter("save")).thenReturn("save");
        when(request.getParameter("addPet")).thenReturn("d");
        when(request.getParameter("petName")).thenReturn("Pele");
        when(request.getParameter("typePet")).thenReturn("cat");
        when(request.getContextPath()).thenReturn("/pcw/view");
        when(request.getRequestDispatcher("/views/client/EditClient.jsp")).thenReturn(dispatcher);

        assertFalse(clinic.getClientById("1").getNameClient().equals("editedClient"));

        new ClientEditServlet().doPost(request,response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("nameClient");
        verify(request, atLeast(1)).getParameter("save");
        verify(request, atLeast(1)).getParameter("addPet");
        verify(request,atLeast(1)).getContextPath();

        assertTrue(clinic.getClientById("1").getNameClient().equals("editedClient"));
        this.clinic.deleteClient("1");

    }

    public void testAddPet() throws ServletException, IOException {

        this.clinic.addClient(new ClientModel("1","test_Client"));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("save")).thenReturn("null");
        when(request.getParameter("addPet")).thenReturn("добавить");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("petName")).thenReturn("Pele");
        when(request.getParameter("typePet")).thenReturn("cat");
        when(request.getRequestDispatcher("/views/client/EditClient.jsp")).thenReturn(dispatcher);

        assertTrue(this.clinic.getPetCurrentClient("1").isEmpty());

        new ClientEditServlet().doPost(request,response);

        verify(request, atLeast(1)).getParameter("save");
        verify(request, atLeast(1)).getParameter("addPet");
        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("petName");
        verify(request, atLeast(1)).getParameter("typePet");
        verify(dispatcher).forward(request, response);

        assertFalse(this.clinic.getClients().iterator().next().getPets().isEmpty());
    }

}
