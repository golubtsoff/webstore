package dao;

import entity.Purchase;
import util.DBService;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public class PurchaseDAOImpl implements PurchaseDAO {

    PurchaseDAOImpl() {
    }

    @Override
    public Purchase get(long id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(Purchase.class, id);
    }

    @Override
    public List<Purchase> getAll() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from Purchase", Purchase.class)
                .list();
    }

    @Override
    public long create(Purchase purchase) {
        return (Long) DBService.getSessionFactory()
                .getCurrentSession()
                .save(purchase);
    }

    @Override
    public void deleteAll(){
        DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from Purchase")
                .executeUpdate();
    }

}
