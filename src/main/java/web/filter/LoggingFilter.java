package web.filter;

import entity.Item;
import entity.Person;
import entity.Role;
import service.AdminService;
import service.AdminServiceImpl;
import service.PersonService;
import service.PersonServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 17.02.2018.
 */

public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        для тестового добавления пользователей
//        TODO remove in production
        addPersons(request, response);
        addItems();

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("person") != null) {
//            chain.doFilter(request, response);
            request.getRequestDispatcher("/items").forward(request, response);
        } else {
//            request.getRequestDispatcher("/WEB-INF/jsp/signin.jsp").forward(request, response);
            request.getRequestDispatcher("/signin").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    private void addPersons(ServletRequest request, ServletResponse response) {
        // sign up person
        PersonService personService = new PersonServiceImpl();
        personService.signUp("root", "123", Role.admin);
        Person person = personService.signUp("user", "345");

        // create session of person
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        session.setAttribute("person", person);
    }

    private void addItems() {
        PersonService personService = new PersonServiceImpl();
        Person personAdmin = personService.signUp("admin", "111");
        AdminService adminService = new AdminServiceImpl(personAdmin);
        List<Item> items = new ArrayList<>();
        items.add(new Item("Танк", "Конструктор для сборки модели танка", 1000, 10));
        items.add(new Item("Велосипед", "Спортивный велосипед для поездки по пересечённой местности", 9999.99, 3));
        items.add(new Item("Воздушный шар", "Шары надувные разноцветные", 10.5, 265));
        for (Item item : items) {
            adminService.createItem(item);
        }
    }
}


