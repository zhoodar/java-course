package kg.djedai.servlets;


import kg.djedai.models.Cat;
import kg.djedai.models.ClientModel;
import kg.djedai.models.Dog;
import kg.djedai.models.Pet;
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


    /**
     * Обработка get-запросов
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardTo(req,resp,CREATE);
    }

    /**
     * Обработка post-запросов
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
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

    /**
     * Процесс добавление клиента к месту хранение
     * @param req запрос
     * @param resp ответ
     * @throws ServletException
     * @throws IOException
     */
    private void processAddClient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("add")!= null) {
            this.idAddingClient = this.CLIENT.generateId();
            this.CLIENT.addClient(createClient(req));
            addPetsToLastClient(req);
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
        }
    }

    /**
     * Добавление животного к последному клиенту
     * @param req запрос
     */
    private void addPetsToLastClient(HttpServletRequest req) {
        this.CLIENT.addPetToClient( createPet(req), this.idAddingClient);
    }

    /**
     * получение тип животного
     * @param req запрос
     * @return объект Pet
     */
    private Pet createPet(HttpServletRequest req) {
        Pet pet;
        if(req.getParameter("typePet").equals("cat")) {
            pet = new Cat(req.getParameter("namePet"));
        } else pet = new Dog(req.getParameter("namePet"));
        return pet;
    }


    /**
     * Создание нового клиента изходя из запроса
     * @param req запрос
     * @return новый клиент
     * @throws ServletException
     * @throws IOException
     */
    private ClientModel createClient(HttpServletRequest req) throws ServletException, IOException{
         return  new ClientModel(idAddingClient,req.getParameter("nameClient"));
    }

}
