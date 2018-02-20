package service;

import entity.Item;
import entity.Person;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */
public interface PersonService {

    Person signIn(String name, String password);

    Person signUp(String name, String password);

    List<Item> getItems();
}