package web;

import entity.Item;
import entity.Person;
import entity.Role;
import org.hibernate.Session;
import service.AdminService;
import service.AdminServiceImpl;
import service.PersonService;
import service.PersonServiceImpl;

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
        if (action == null) {
            PersonService personService = new PersonServiceImpl();
            request.setAttribute("items", personService.getItems());
            request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            PersonService personService = new PersonServiceImpl();
            request.setAttribute("items", personService.getItems());
            request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
        }

        HttpSession session = request.getSession(false);
        Person person = (Person) session.getAttribute("person");
        String itemIdString = request.getParameter("id");
        Long itemId = itemIdString == null ? null : Long.parseLong(itemIdString);
        if (person.getRole() == Role.admin){
            AdminService adminService = new AdminServiceImpl();
            if (action.equalsIgnoreCase("delete")){
                adminService.deleteItem(itemId);
                response.sendRedirect("items");
            } else if (action.equalsIgnoreCase("view")){
                Item item = adminService.getItem(itemId);
                request.setAttribute("item", item);
                request.getRequestDispatcher("/WEB-INF/jsp/view_item.jsp").forward(request, response);
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
        }
    }
}
