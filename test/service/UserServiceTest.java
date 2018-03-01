package service;

import entity.Item;
import entity.Person;
import exception.DBException;
import exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class UserServiceTest {

    private final PersonService personService = new PersonServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test (expected = DBException.class)
    public void setPurchaseDBException() throws Exception {
        Person person = personService.signUp("Bob", "pass345");
        UserService userService = new UserServiceImpl(person);
        userService.setPurchase(-1, -1);
    }

    @Test (expected = ServiceException.class)
    public void setPurchaseServiceException() throws Exception {
        Person person = personService.signUp("Bob", "pass345");
        UserService userService = new UserServiceImpl(person);
        Item item = new Item("Гравитационный 3D лабиринт",
                "\"Гравитационный 3D-лабиринт\" - объемная головоломка от компании ThinkFun, увидевшая свет в 2017 году. Решая ее задания, игроку предстоит проложить маршрут для металлического шарика, который скатывается под собственным весом по выстраиваемому лабиринту.\n" +
                        "\n" +
                        "      Игра-головоломка \"Гравитационный 3D Лабиринт\", в основе которой Закон всемирного тяготения, несомненно, станет достойным испытанием для ваших умственных способностей и пространственного воображения. ",
                new BigDecimal(3290),
                0);

        Long itemId = adminService.createItem(item);
        userService.setPurchase(itemId, 1);
    }

}