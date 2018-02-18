package service;

import entity.Item;
import entity.Person;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
//TODO implement methods
public class UserServiceImpl extends PersonServiceImpl implements UserService {

    public UserServiceImpl(Person person){
        this.person = person;
    }

    @Override
    public long setPurchase(Item item, int amount) {
        return 0;
    }
}
