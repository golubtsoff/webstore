package service;

import entity.Item;
import entity.Purchase;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public interface AdminService extends PersonService {

    long createItem(Item item);

    void updateItem(Item item);

    void deleteItem(long id);

    List<Purchase> getPurchases();
}
