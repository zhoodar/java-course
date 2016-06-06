package kg.djedai.servlets;

import kg.djedai.app.clinic.Cat;
import kg.djedai.models.ClientModel;
import kg.djedai.store.ClientCache;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by User on 06.06.2016.
 */
public class ClientDeleteServletTest extends Mockito{

    final ClientCache clinic = ClientCache.getInstance();

    @Test
    public void testClientDelete() throws ServletException, IOException {
        this.clinic.addClient(new ClientModel(1,"test_Client",new Cat("testPet")));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getContextPath()).thenReturn("/pcw");

        assertEquals(1,this.clinic.findByFullName("test_Client").size());

        new ClientDeleteServlet().doGet(request,response);

        verify(request,atLeast(1)).getParameter("id");
        verify(request,atLeast(1)).getContextPath();


    }

}