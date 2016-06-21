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
import java.util.List;

/**
 * @author Zhoodar
 * @since 31.05.2016.
 */
public class ClientEditServlet extends HttpServlet {

    private static final String EDIT = "/views/client/EditClient.jsp";

    private final ClientCache CLIENT = ClientCache.getInstance();

    /**
     * Обработка get-запросов
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAttributes(req);
        forwardTo(req,resp,EDIT);
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
        processEdit(req , resp);
        processAddPet(req, resp);
    }

    /**
     * Установеление атрибутов для показа
     * @param req запрос
     */
    private void setAttributes(HttpServletRequest req) {
        req.setAttribute("client", this.CLIENT.getClientById(req.getParameter("id")));
        req.setAttribute("pets", this.CLIENT.getPetCurrentClient(req.getParameter("id"))) ;
    }

    /**
     * Редактирвание клиента
     * @param req запрос
     * @param resp ответ
     * @throws IOException
     */
    private void processEdit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getParameter("save")!= null){
            this.CLIENT.editClient(newClient(req));
            resp.sendRedirect(String.format("%s%s", req.getContextPath(), "/view"));
        }
    }

    /**
     * Добавление нового животного к текушему клиенту
     * @param req запрос
     * @param resp ответ
     * @throws ServletException
     * @throws IOException
     */
    private void processAddPet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("addPet")!= null) {
            this.CLIENT.addPetToClient(getType(req),req.getParameter("petName"),req.getParameter("id"));
            doGet(req, resp);
        }
    }

    /**
     * Получение типа животного
     * @param req запрос
     * @return int тип
     */
    private int getType(HttpServletRequest req) {
        return (req.getParameter("typePet").equals("cat"))? 2 : 1;
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
     * Редактирование объекта клиент и прекрыпление всех животных к созданному клиенту
     * @param req запрос
     * @return редактированный клиент
     */
    private ClientModel newClient(HttpServletRequest req){
        List<Pet> pets = this.CLIENT.getClientById(req.getParameter("id")).getPet();
        ClientModel client = new ClientModel(req.getParameter("id"),req.getParameter("nameClient"));
        for(Pet pet : pets) {
            client.setPets(pet);
        }
        return  client;
    }
}
