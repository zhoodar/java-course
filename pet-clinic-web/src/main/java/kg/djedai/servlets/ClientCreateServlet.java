package kg.djedai.servlets;

import kg.djedai.app.clinic.Animal;
import kg.djedai.app.clinic.Cat;
import kg.djedai.app.clinic.Dog;
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
    private static int generatedId = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardTo(req,resp,CREATE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        generatedId = this.CLIENT.generateId() + 1;
        this.processAddClient(req, resp);
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
        if(req.getParameter("add")!= null)
           this.addClient(req,resp);
    }

    private void addClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.CLIENT.addClient(this.createClient(req));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/"));
    }

    private ClientModel createClient(HttpServletRequest req) throws ServletException, IOException{
        ClientModel created = null;
        if(req.getParameter("typePet").equals("cat")) {
            created = new ClientModel(generatedId,req.getParameter("nameClient"),
                    new Cat(req.getParameter("namePet")));
        }
        if(req.getParameter("typePet").equals("dog")) {
            created = new ClientModel(generatedId,req.getParameter("nameClient"),
                        new Dog(new Animal(req.getParameter("namePet"))));
        }
        return created;
    }

}
