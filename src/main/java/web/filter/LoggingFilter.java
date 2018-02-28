package web.filter;

import entity.Item;
import entity.Person;
import entity.Role;
import exception.DBException;
import exception.ServiceException;
import service.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 17.02.2018.
 */

public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null && session.getAttribute("person") != null) {
            request.getRequestDispatcher("/items").forward(request, response);

        } else {
            request.getRequestDispatcher("/signin").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig config){
//        для тестового добавления пользователей
//        TODO remove in production
        try {
            addPersons();
            addItems();
            addPurchases();
        } catch (DBException | ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

    private void addPersons() throws DBException {
        // sign up person
        PersonService personService = new PersonServiceImpl();
        personService.signUp("root", "123", Role.admin);
        personService.signUp("user", "345");
    }

    private void addItems() throws DBException {
        PersonService personService = new PersonServiceImpl();
        personService.signUp("alice", "111");
        AdminService adminService = new AdminServiceImpl();
        List<Item> items = new ArrayList<>();
        items.add(new Item("Танк", "Конструктор для сборки модели танка", new BigDecimal(1000), 10));
        items.add(new Item("Велосипед", "Спортивный велосипед для поездки по пересечённой местности", new BigDecimal(9999.99), 1));
        items.add(new Item("Воздушный шар", "Шары надувные разноцветные", new BigDecimal(10.5), 265));
        for (Item item : items) {
            Long id = adminService.createItem(item);
            if (id == 1L){
                adminService.deleteItem(id);
            }
        }

    }

    private void addPurchases() throws DBException, ServiceException {
        PersonService personService = new PersonServiceImpl();
        Person person = personService.signUp("user2", "secret");
        AdminService adminService = new AdminServiceImpl();
        Item item = new Item(
                "Космический шаттл",
                "Конструктор для сборки комического корабля",
                new BigDecimal(10000.01),
                5);
        Long itemId = adminService.createItem(item);

        UserService userService = new UserServiceImpl(person);
        userService.setPurchase(itemId, 3);
    }
}


