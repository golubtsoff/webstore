package service;

import entity.Item;
import entity.Person;
import entity.Role;
import exception.DBException;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */
public interface PersonService {

    Person signIn(String name, String password) throws DBException;

    Person signUp(String name, String password) throws DBException;

    Person signUp(String name, String password, Role role) throws DBException;

    Item getItem(long id) throws DBException;

    List<Item> getItems(Person person) throws DBException;
}
