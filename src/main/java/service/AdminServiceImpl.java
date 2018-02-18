package service;

import entity.Item;
import entity.Person;
import entity.Purchase;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */

//TODO implement methods
public class AdminServiceImpl extends PersonServiceImpl implements AdminService {

    public AdminServiceImpl(Person person){
        this.person = person;
    }

    @Override
    public int createItem(Item item) {
        return 0;
    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void deleteItem(Item item) {

    }

    @Override
    public List<Purchase> getPurchases() {
        return null;
    }
}
