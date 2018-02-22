package web;

import service.PersonService;
import service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO implementation doGet and doPost
public class ItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            PersonService personService = new PersonServiceImpl();
            request.setAttribute("items", personService.getItems());
            request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            PersonService personService = new PersonServiceImpl();
            request.setAttribute("items", personService.getItems());
            request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
        }
    }
}
