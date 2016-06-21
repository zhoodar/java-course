package kg.djedai.servlets;

import kg.djedai.store.ClientCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Zhoodar
 * @since 31.05.2016.
 */
public class ClientDeleteServlet extends HttpServlet {

    private final ClientCache CLIENT = ClientCache.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.CLIENT.deleteClient((req.getParameter("id")));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.CLIENT.deletePetCurrentClient(req.getParameter("id"), req.getParameter("petName"));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/client/edit?id="+req.getParameter("id")));
    }
}
