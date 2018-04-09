package service;

import entity.Person;
import entity.Role;
import exception.DBException;

import java.util.ArrayList;
import java.util.List;

import static data.TestData.*;
import static data.TestData.adminService;
import static data.TestData.item3;

public abstract class ServiceTest {

    protected List<Long> itemsId = new ArrayList<>();
    protected Person personSigned;
    protected Person personNotSigned;
    protected Person personAdmin;

    protected void initData() throws DBException{
        adminService.deleteAllItems();
        adminService.deleteAllPersons();
        adminService.deleteAllPurchases();

        personSigned = personService.signUp("Bob", "pass345");
        personAdmin = personService.signUp("admin", "pass123", Role.admin);
        personNotSigned = new Person("Alex", "pass123");

        itemsId.add(adminService.createItem(item1));
        itemsId.add(adminService.createItem(item2));
        itemsId.add(adminService.createItem(item3));
    }
}
