package kg.djedai.servlets;

import kg.djedai.app.clinic.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * @author Zhoodar
 * 28.05.2016.
 */
public class ClinicServlet extends HttpServlet{
    final List<Client> clients = new CopyOnWriteArrayList<Client>();
    private String foundName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "     <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "         <b>Find</b><br> <input type='text' name='search'>" +
                        "               <input type='submit' value='search'>" +
                        "     <form>"+
                        this.viewResult(this.foundName)+
                        "     <form action='"+req.getContextPath()+"/' method='post'>" +
                        "        <p> <b>Add data </b> </p>" +
                        "          Client name<br><input type='text' name='name'><br>"+
                        "          Client's pet name<br><input type='text' name='pet_name'>"+
                        "         <input type='submit' value='Submit'>"+
                        "     <form>"+
                        this.viewPets() +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getParameter("name").isEmpty() && !req.getParameter("pet_name").isEmpty())
            this.clients.add(new Client(req.getParameter("name"),new Dog(new Animal(req.getParameter("pet_name")))));
        if(!req.getParameter("search").isEmpty())
            this.foundName = req.getParameter("search");
        doGet(req, resp);
    }

    private String viewPets() {
        StringBuilder sb = new StringBuilder();
        sb.append("<p><b>All pets<b></p>");
        sb.append("<table border='1px' >");
        sb.append("<tr><td >").append("Client name").append("</td><td>").append("Pet name").append("</td></tr>");
        for (Client client : this.clients) {
            sb.append("<tr><td >").append(client.getId()).append("</td><td>").append(client.getPet().getName()).append("</td></tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
    private String viewResult(String foundName){
        StringBuilder sb = new StringBuilder();
        boolean isFound =false;
        if(!this.clients.isEmpty() && this.foundName!=null) {
            sb.append("<p><b>Result of search:</b>");
            sb.append("<table>");
            sb.append("<tr><td>").append("Pet's owner").append("</td><td>").append("pet name").append("</td></tr>");
            for (Client client : this.clients) {
                if(foundName.equals(client.getPet().getName()) || foundName.equals(client.getId()) ) {
                    sb.append("<tr><td>").append(client.getId()).append("</td><td>").append(client.getPet().getName()).append("</td></tr>");
                    sb.append("</table>");
                    isFound = true;
                    break;
                }
            }
            if(!isFound) {
                sb.delete(0, sb.capacity());
                sb.append("<p><b>Result of search:</b>");
                sb.append("<br>Not found! ");
            }
        }
        sb.append("</p>");

        return sb.toString();
    }

}