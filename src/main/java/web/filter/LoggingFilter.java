package web.filter;

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
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("person") != null) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException{

    }

    @Override
    public void destroy(){

    }
}


