package test.service;

import entity.Item;
import entity.Person;
import service.AdminService;
import service.AdminServiceImpl;
import service.PersonService;
import service.PersonServiceImpl;

import java.math.BigDecimal;

/**
 * Created by Evgeniy Golubtsov on 23.02.2018.
 */
public class AdminServiceTest {
    public static void main(String[] args) {
        PersonService personService = new PersonServiceImpl();
        Person person = personService.signUp("user", "secret");
        AdminService adminService = new AdminServiceImpl(person);
        Item item = new Item(
                "Космический шаттл",
                "Конструктор для сборки комического корабля",
                new BigDecimal(10000.01),
                1);
        adminService.createItem(item);
    }
}
