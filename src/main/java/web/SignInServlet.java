package web;

import entity.Item;
import entity.Person;
import exception.DBException;
import org.hibernate.Session;
import service.PersonService;
import service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()){
            return;
        }

        PersonService personService = new PersonServiceImpl();
        Person person;
        try{
            person = personService.signIn(login, password);
        } catch (DBException e){
            person = null;
        }

        if (person != null) {
            request.getSession(true).setAttribute("person", person);
            request.getRequestDispatcher("/items").forward(request, response);
        } else {
            response.setContentType("text/plain;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
    }
}

