package kg.djedai.servlets;

import kg.djedai.app.clinic.Pet;
import kg.djedai.models.ClientModel;
import kg.djedai.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhoodar
 * @since 31.05.2016.
 */

public class ClientViewServlet extends HttpServlet {

    private static final String VIEW = "/views/client/ClientView.jsp";
    private final ClientCache CLIENT = ClientCache.getInstance();
    private final List<ClientModel> readyClients = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("clients", this.CLIENT.getClients());
        forwardTo(req,resp, VIEW);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    /**
     *
     * @param req
     * @param resp
     * @param path
     * @throws ServletException
     * @throws IOException
     */
    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }
}
