package kg.djedai.servlets;

import kg.djedai.models.Cat;
import kg.djedai.models.Pet;
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
 *@author Zhoodar
 * @since 05.06.2016.
 */

public class ClinicViewServletTest extends Mockito {

    final ClientCache clinic = ClientCache.getInstance();


@Test
    public void testClinicViewSearch() throws ServletException, IOException {
        this.clinic.addClient(new ClientModel("1","test_Client"));
        Pet cat = new Cat("cat");
        this.clinic.addPetToClient(cat,"1");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("search")).thenReturn("test");
        when(request.getParameter("name")).thenReturn("test_Client");
        when(request.getParameter("typeSearch")).thenReturn("on");
        when(request.getRequestDispatcher("/views/clinic/Index.jsp")).thenReturn(dispatcher);

        new ClinicViewServlet().doPost(request,response);

        verify(request, atLeast(1)).getParameter("search");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("typeSearch");
        verify(dispatcher).forward(request, response);

        this.clinic.deleteClient("1");

    }

    @Test
    public void testClinicViewEmptySearch() throws  ServletException, IOException {
        this.clinic.addClient(new ClientModel("1","test_Client"));
        Pet cat = new Cat("cat");
        this.clinic.addPetToClient(cat,"1");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("search")).thenReturn("test");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("typeSearch")).thenReturn("");
        when(request.getRequestDispatcher("/views/clinic/Index.jsp")).thenReturn(dispatcher);

        new ClinicViewServlet().doPost(request,response);

        verify(request, atLeast(1)).getParameter("search");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(0)).getParameter("typeSearch");
        verify(dispatcher).forward(request, response);

        this.clinic.deleteClient("1");
    }

    @Test
    public void testClinicViewSearchContain() throws  ServletException, IOException {
        this.clinic.addClient(new ClientModel("1","test_Client"));
        Pet cat = new Cat("cat");
        this.clinic.addPetToClient(cat,"1");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getParameter("search")).thenReturn("test");
        when(request.getParameter("name")).thenReturn("te");
        when(request.getParameter("typeSearch")).thenReturn(null);
        when(request.getRequestDispatcher("/views/clinic/Index.jsp")).thenReturn(dispatcher);

        new ClinicViewServlet().doPost(request,response);

        verify(request, atLeast(1)).getParameter("search");
        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("typeSearch");
        verify(dispatcher).forward(request, response);
    }

}
