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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zhoodar
 * @since 31.05.2016.
 */
public class ClientEditServlet extends HttpServlet {

    private static final String EDIT = "/views/client/EditClient.jsp";

    private final ClientCache CLIENT = ClientCache.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("client", this.CLIENT.get(Integer.valueOf(req.getParameter("id"))));
        forwardTo(req,resp,EDIT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.CLIENT.editClient(this.newClient(req));
        resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/"));
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

    private ClientModel newClient(HttpServletRequest req){
        ClientModel created = null;
        if(req.getParameter("typePet").equals("cat")) {
            created = new ClientModel(Integer.parseInt(req.getParameter("id")),req.getParameter("nameClient"),
                    new Cat(req.getParameter("namePet")));
        }
        if(req.getParameter("typePet").equals("dog")) {
            created = new ClientModel(Integer.parseInt(req.getParameter("id")),req.getParameter("nameClient"),
                    new Dog(new Animal(req.getParameter("namePet"))));
        }
        return created;
    }
}
