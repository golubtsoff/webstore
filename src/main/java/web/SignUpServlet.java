package web;

import entity.Person;
import entity.Role;
import exception.DBException;
import service.PersonService;
import service.PersonServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SignUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retype_password");

        if (login == null || password == null || retypePassword == null
                || login.isEmpty() || password.isEmpty() || retypePassword.isEmpty()
                || !password.equals(retypePassword)){
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
            return;
        }

        PersonService personService = new PersonServiceImpl();
        Person person;
        try{
            person = personService.signUp(login, password);
        } catch (DBException e){
            person = null;
        }

        if (person != null) {
            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
        } else {
            response.setContentType("text/plain;charset=utf-8");
            response.getWriter().println("Not registered");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp").forward(request, response);
    }
}

