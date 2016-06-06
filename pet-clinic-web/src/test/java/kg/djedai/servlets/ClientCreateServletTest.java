package kg.djedai.servlets;

import kg.djedai.store.ClientCache;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by User on 04.06.2016.
 */
public class ClientCreateServletTest extends Mockito{

    final ClientCache cache = ClientCache.getInstance();

    @Test
    public void testCreateClient() throws ServletException, IOException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("add")).thenReturn("test");
        when(request.getParameter("nameClient")).thenReturn("test");
        when(request.getParameter("typePet")).thenReturn("cat");
        when(request.getParameter("namePet")).thenReturn("test");

        assertTrue(cache.values().isEmpty());

        new ClientCreateServlet().doPost(request,response);

        verify(request,atLeast(1)).getParameter("nameClient");
        verify(request,atLeast(1)).getParameter("typePet");
        verify(request,atLeast(1)).getParameter("namePet");

        assertFalse(cache.values().isEmpty());

    }

}