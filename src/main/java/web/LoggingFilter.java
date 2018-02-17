package web;

import entity.Person;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Evgeniy Golubtsov on 17.02.2018.
 */

public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);

        if (session.getAttribute("person") != null) {
            chain.doFilter(request, response);
        } else if ((req.getParameter("login") != null) && (req.getParameter("password") != null)) {
            req.getRequestDispatcher("/login").forward(req, res);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, res);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException{

    }

    @Override
    public void destroy(){

    }
}


