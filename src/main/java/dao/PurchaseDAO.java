package dao;

import entity.Purchase;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 10.02.2018.
 */
public interface PurchaseDAO extends Dao{

    Purchase get(long id);

    List<Purchase> getAll();

    long create(Purchase purchase);

    void deleteAll();
}
