package service;

import entity.Item;
import entity.Purchase;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public interface AdminService {

    boolean signIn(String name, String password);

    List<Item> getItems();

    int createItem(Item item);

    void updateItem(Item item);

    void deleteItem(Item item);

    List<Purchase> getPurchases();
}
