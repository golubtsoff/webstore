package service;

import entity.Item;
import entity.Purchase;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public interface UserService {

    boolean signUp(String name, String password);

    boolean signIn(String name, String password);

    List<Item> getItems();

    long setPurchase(Item item, int amount);
}
