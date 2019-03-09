package web;

import entity.Item;
import entity.Person;
import exception.DBException;
import exception.ServiceException;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

public class ItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            Item item = getItem(request);
            AdminService adminService = ServiceFactory.getService(AdminService.class);

            assert adminService != null;
            if (item.getId() > 0) {
                adminService.updateItem(item);
            } else {
                adminService.createItem(item);
            }
            response.sendRedirect("items");
        } catch (ServiceException e) {
            try {
                request.setCharacterEncoding("UTF-8");
                Item item = new Item(-1L, "", "", new BigDecimal(0), 0);
                request.setAttribute("item", item);
                request.setAttribute("exception", "Input data is not correct");
                request.getRequestDispatcher("/WEB-INF/jsp/edit_item.jsp").forward(request, response);
            } catch (Exception e1) {
                response.sendRedirect("error");
            }
        } catch (Exception e){
            response.sendRedirect("error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Person person = (Person) session.getAttribute("person");
        String action = request.getParameter("action");
        PersonService personService = ServiceFactory.getService(PersonService.class);

        assert personService != null;
        if (action == null) {
            try {
                request.setAttribute("items", personService.getItems(person));
                request.getRequestDispatcher("/WEB-INF/jsp/view_items.jsp").forward(request, response);
            } catch (DBException e) {
                response.sendRedirect("error");
            }
            return;
        }

        String itemIdString = request.getParameter("id");

        if (action.equalsIgnoreCase("view") && itemIdString != null) {
            try {
                Item item = personService.getItem(Long.parseLong(itemIdString));
                request.setAttribute("item", item);
                request.getRequestDispatcher("/WEB-INF/jsp/view_item.jsp").forward(request, response);
            } catch (DBException e) {
                response.sendRedirect("error");
            }
            return;
        }

        switch (person.getRole()) {
            case admin:
                AdminService adminService = ServiceFactory.getService(AdminService.class);

                assert adminService != null;
                if (action.equalsIgnoreCase("delete") && itemIdString != null) {
                    try {
                        adminService.deleteItem(Long.parseLong(itemIdString));
                        response.sendRedirect("items");
                    } catch (DBException e) {
                        response.sendRedirect("error");
                    }
                } else if (action.equalsIgnoreCase("edit") && itemIdString != null) {
                    try {
                        Item item = adminService.getItem(Long.parseLong(itemIdString));
                        request.setAttribute("item", item);
                        request.getRequestDispatcher("/WEB-INF/jsp/edit_item.jsp").forward(request, response);
                    } catch (DBException e) {
                        response.sendRedirect("error");
                    }

                } else if (action.equalsIgnoreCase("add")) {
                    Item item = new Item(-1L, "", "", new BigDecimal(0), 0);
                    request.setAttribute("item", item);
                    request.getRequestDispatcher("/WEB-INF/jsp/edit_item.jsp").forward(request, response);
                } else {
                    response.sendRedirect("error");
                }
                break;
            case user:
                UserService userService = ServiceFactory.getService(UserService.class);
                assert userService != null;
                if (action.equalsIgnoreCase("buy") && itemIdString != null) {
                    try {
                        userService.setPurchase(Long.parseLong(itemIdString), 1, person);
                        response.sendRedirect("items");
                    } catch (DBException | ServiceException e) {
                        response.sendRedirect("error");
                    }

                } else {
                    response.sendRedirect("error");
                }
                break;
            default:
                response.sendRedirect("error");
                break;
        }
    }

    private Item getItem(HttpServletRequest request) throws ServiceException {
        try {
            String idString = request.getParameter("id");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String priceString = request.getParameter("price");
            String amountString = request.getParameter("amount");

            if (idString == null || idString.isEmpty()
                    || title == null || title.isEmpty()
                    || priceString == null || priceString.isEmpty()
                    || amountString == null || amountString.isEmpty()){
                throw new ServiceException("A data-entry error");
            }

            Long id = Long.parseLong(idString);
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceString));
            Integer amount = Integer.parseInt(amountString);
            return new Item(id, title, description, price, amount);
        } catch (Exception e){
            throw new ServiceException("Error added items", e);
        }
    }
}
