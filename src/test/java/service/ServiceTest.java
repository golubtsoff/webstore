package service;

import entity.Person;
import entity.Role;
import exception.DBException;

import java.util.ArrayList;
import java.util.List;

import static data.TestData.*;

abstract class ServiceTest {

    final List<Long> itemsId = new ArrayList<>();
    Person personSigned;
    Person personNotSigned;
    Person personAdmin;

    void initData() throws DBException{
        assert adminService != null;
        adminService.deleteAllItems();
        adminService.deleteAllPersons();
        adminService.deleteAllPurchases();

        assert personService != null;
        personSigned = personService.signUp("Bob", "pass345");
        personAdmin = personService.signUp("admin", "pass123", Role.admin);
        personNotSigned = new Person("Alex", "pass123");

        itemsId.add(adminService.createItem(item1));
        itemsId.add(adminService.createItem(item2));
        itemsId.add(adminService.createItem(item3));
    }
}
