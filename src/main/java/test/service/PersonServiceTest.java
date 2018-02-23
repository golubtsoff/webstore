package test.service;

import service.PersonService;
import service.PersonServiceImpl;

/**
 * Created by Evgeniy Golubtsov on 23.02.2018.
 */
public class PersonServiceTest {
    public static void main(String[] args) {
        PersonService personService = new PersonServiceImpl();
        personService.signUp("admin", "123");
    }
}
