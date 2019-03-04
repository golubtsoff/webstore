package service;

import entity.Item;
import entity.Purchase;
import exception.DBException;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public interface AdminService extends PersonService {

    long createItem(Item item) throws DBException;

    void updateItem(Item item) throws DBException;

    void deleteItem(long id) throws DBException;

    void deleteAllItems() throws DBException;

    void deleteAllPersons() throws DBException;

    void deleteAllPurchases() throws DBException;

    List<Purchase> getPurchases() throws DBException;
}
