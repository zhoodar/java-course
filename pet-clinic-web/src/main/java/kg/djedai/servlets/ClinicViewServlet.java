package kg.djedai.servlets;

import kg.djedai.models.ClientModel;
import kg.djedai.store.ClientCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Zhoodar
 * @since 31.05.2016.
 */
public class ClinicViewServlet  extends HttpServlet {

    private static final String VIEW = "/views/clinic/Index.jsp";
    private final ClientCache CLIENT = ClientCache.getInstance();

    private final List<ClientModel> foundClient = new CopyOnWriteArrayList<ClientModel>();
    private boolean isSearching = false;
    private String nameForSearch = "";

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processContentView(req);
        forwardTo(req,resp,VIEW);
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processSearch(req);
        this.nameForSearch = req.getParameter("name");
        doGet(req,resp);
    }

    /**
     *
     * @param req
     */
    private  void setDoGet(HttpServletRequest req){
        req.setAttribute("results", this.foundClient);
        req.setAttribute("content", req.getParameter("search") != null);

    }

    /**
     *
     * @param req
     */
    private void processSearch(HttpServletRequest req) {
        if (req.getParameter("search") != null) {
            this.foundClient.clear();
            this.isSearching = true;
        }
    }

    /**
     *
     * @param req
     */
    private void processContentView(HttpServletRequest req) {
        if(this.isSearching)
            showResultOfSearch(req);
        this.setDoGet(req);
    }


    /**
     * показ резултат поиска
     * @param req
     */
    private void showResultOfSearch(HttpServletRequest req) {
        if(req.getParameter("typeSearch")!= null) {
            this.foundClient.addAll(this.CLIENT.findByFullName(req.getParameter(req.getParameter("name"))));
        } else {
            this.foundClient.addAll(this.CLIENT.findByContain(req.getParameter(req.getParameter("name"))));
        }
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

