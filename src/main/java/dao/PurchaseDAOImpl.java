package dao;

import entity.Purchase;
import org.hibernate.Session;
import util.DBService;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public class PurchaseDAOImpl implements PurchaseDAO {

    @Override
    public Purchase get(long id) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        return session.get(Purchase.class, id);
    }

    @Override
    public List<Purchase> getAll() {
        Session session = DBService.getSessionFactory().getCurrentSession();
        return session.createQuery("from Purchase", Purchase.class).list();
    }

    @Override
    public long create(Purchase purchase) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        return (Long) session.save(purchase);
    }

}
