package web;

import entity.Person;
import exception.DBException;
import service.PersonService;
import service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()){
            request.setAttribute("exception", "Not autorized");
            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
            return;
        }

        PersonService personService = ServiceFactory.getService(PersonService.class);

        assert personService != null;
        Person person;
        try{
            person = personService.signIn(login, password);
        } catch (DBException e){
            person = null;
        }

        if (person != null) {
            request.getSession(true).setAttribute("person", person);
            response.sendRedirect("items");
        } else {
            request.setAttribute("exception", "Not autorized");
            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
    }
}

