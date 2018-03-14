package web.filter;

import util.DBService;
import util.DataUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
            request.getRequestDispatcher("/items").forward(request, response);

        } else {
            request.getRequestDispatcher("/signin").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) {
//        для тестового добавления пользователей
        if (DBService.isTesting()){
            DataUtil dataUtil = new DataUtil();
            dataUtil.fillDB();
        }
    }

    @Override
    public void destroy() {
        DBService.close();
    }
}


