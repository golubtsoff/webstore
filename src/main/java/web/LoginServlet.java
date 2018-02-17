package web;

import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

    }
}

