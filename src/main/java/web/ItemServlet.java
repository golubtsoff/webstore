package web;

import entity.Item;
import entity.Person;
import entity.Role;
import exception.ServiceException;
import org.hibernate.Session;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

// TODO implementation doGet and doPost
public class ItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        Long id = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(request.getParameter("price")));
        Integer amount = Integer.parseInt(request.getParameter("amount"));

        if (id == null || title == null || price == null || amount == null){
            throw new ServiceException("Error added items.");
        }

        Item item = new Item(id, title, description, price, amount);
        AdminService adminService = new AdminServiceImpl();
        if (item.getId() > 0){
            adminService.updateItem(item);
        } else {
            adminService.createItem(item);
        }
        response.sendRedirect("items");

//        if (action == null) {
//            PersonService personService = new PersonServiceImpl();
//            request.setAttribute("items", personService.getItems());
//            request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Person person = (Person) session.getAttribute("person");
        String action = request.getParameter("action");
        PersonService personService = new PersonServiceImpl();
        if (action == null) {
            request.setAttribute("items", personService.getItems(person));
            request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
            return;
        }

        String itemIdString = request.getParameter("id");
        Long itemId = itemIdString == null ? null : Long.parseLong(itemIdString);

        if (action.equalsIgnoreCase("view")){
            Item item = personService.getItem(itemId);
            request.setAttribute("item", item);
            request.getRequestDispatcher("/WEB-INF/jsp/view_item.jsp").forward(request, response);
            return;
        }

        if (person.getRole() == Role.admin){
            AdminService adminService = new AdminServiceImpl();
            if (action.equalsIgnoreCase("delete")){
                adminService.deleteItem(itemId);
                response.sendRedirect("items");
            } else if (action.equalsIgnoreCase("edit")){
                Item item = adminService.getItem(itemId);
                request.setAttribute("item", item);
                request.getRequestDispatcher("/WEB-INF/jsp/edit_item.jsp").forward(request, response);
            } else if (action.equalsIgnoreCase("add")){
                Item item = new Item(-1L, "", "", new BigDecimal(0), 0);
                request.setAttribute("item", item);
                request.getRequestDispatcher("/WEB-INF/jsp/edit_item.jsp").forward(request, response);
            } else {
                throw new IllegalArgumentException("Action " + action + " is illegal");
            }
        } else if (person.getRole() == Role.user){
            UserService userService = new UserServiceImpl(person);
            if (action.equalsIgnoreCase("buy")){
                userService.setPurchase(itemId, 1);
                response.sendRedirect("items");
            } else {
                throw new IllegalArgumentException("Action " + action + " is illegal");
            }
        } else {
            throw new ServiceException("Unknown person's role");
        }
    }
}
