package kg.djedai.servlets;

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
 * @since 21.06.2016.
 */
public class ClientViewServletTest extends Mockito{

    @Test
    public void testClientView() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/views/client/ClientView.jsp")).thenReturn(dispatcher);
        new ClientViewServlet().doPost(request,response);

        verify(dispatcher).forward(request, response);

    }

}