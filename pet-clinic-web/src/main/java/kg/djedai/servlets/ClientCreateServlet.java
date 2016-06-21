package kg.djedai.servlets;

import kg.djedai.app.clinic.Animal;
import kg.djedai.app.clinic.Cat;
import kg.djedai.app.clinic.Dog;
import kg.djedai.app.clinic.Pet;
import kg.djedai.models.ClientModel;
import kg.djedai.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Zhoodar
 * @since 31.05.2016.
 */
public class ClientCreateServlet extends HttpServlet {

    private static final String CREATE = "/views/client/CreateClient.jsp";

    private final ClientCache CLIENT = ClientCache.getInstance();
    private String idAddingClient="";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardTo(req,resp,CREATE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processAddClient(req, resp);
    }

    /**
     * Перенаправление на указанный адреc
     * @param req Запрос
     * @param resp Ответ
     * @param path Адрес
     * @throws ServletException
     * @throws IOException
     */
    private void forwardTo(HttpServletRequest req, HttpServletResponse resp, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(path);
        dispatcher.forward(req, resp);
    }

    private void processAddClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("add")!= null) {
            this.idAddingClient = this.CLIENT.generateId();
            this.CLIENT.addClient(createClient(req));
            addPetsToLastClient(req);
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
        }
    }

    private void addPetsToLastClient(HttpServletRequest req) {
        this.CLIENT.addPetToClient( getType(req), req.getParameter("namePet"), this.idAddingClient);
    }

    private int getType(HttpServletRequest req) {
       return (req.getParameter("typePet").equals("cat"))? 2 : 1;
    }


    private ClientModel createClient(HttpServletRequest req) throws ServletException, IOException{
         return  new ClientModel(idAddingClient,req.getParameter("nameClient"));
    }

}
