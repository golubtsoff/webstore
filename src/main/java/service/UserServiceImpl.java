package service;

import entity.Item;
import entity.Person;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
//TODO implement methods
public class UserServiceImpl implements UserService {

    private Person person;

    public UserServiceImpl(Person person){
        this.person = person;
    }

    @Override
    public boolean signUp(String name, String password) {
        return false;
    }

    @Override
    public boolean signIn(String name, String password) {
        return false;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public long setPurchase(Item item, int amount) {
        return 0;
    }
}
