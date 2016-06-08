package kg.djedai.servlets;

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
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Zhoodar
 * @since 31.05.2016.
 */
public class ClinicViewServlet  extends HttpServlet {

    private static final String VIEW = "/views/clinic/Index.jsp";
    private final ClientCache CLIENT = ClientCache.getInstance();

    private  List<ClientModel> foundClient = new CopyOnWriteArrayList<ClientModel>();


    /**
     * Обработка get-запросов
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processContentView(req,resp);

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
        processSearch(req);
        doGet(req,resp);
    }

    /**
     * процес начало показа контентта
     * @param req Запрос
     * @param resp Ответ
     * @throws ServletException
     * @throws IOException
     */
    private void processContentView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAttributes(req);
        forwardTo(req,resp,VIEW);
    }

    /**
     * установака атрубутов запроса Get
     * @param req Запрос
     */
    private  void setAttributes(HttpServletRequest req){
        req.setAttribute("results", foundClient);
        req.setAttribute("content", req.getParameter("search") != null);
    }

    /**
     * процес поиска, иницализация
     * @param req Запрос
     */
    private void processSearch(HttpServletRequest req) {

        if (req.getParameter("search") != null) {
            if(!req.getParameter("name").equals("")) {
                this.foundClient.clear();
                this.foundClient.addAll(CLIENT.values());
                findClient(req);
            } else {
                req.setAttribute("error", true);
            }
        }
    }

    /**
     * найти клиента
     * @param req Запрос
     */
    private void findClient(HttpServletRequest req) {
        String searchName = req.getParameter("name");
            if(!searchName.equals("")) {
                if (req.getParameter("typeSearch") != null) {
                    this.foundClient.retainAll(this.CLIENT.findByFullName(searchName));
                } else {
                    this.foundClient.retainAll(this.CLIENT.findByContain(searchName));
            }
       }
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

}

