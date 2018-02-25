package test.service;

import entity.Item;
import entity.Person;
import service.*;

import java.math.BigDecimal;

/**
 * Created by Evgeniy Golubtsov on 23.02.2018.
 */
public class UserServiceTest {
    public static void main(String[] args) {
        PersonService personService = new PersonServiceImpl();
        Person person = personService.signUp("user", "secret");
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
