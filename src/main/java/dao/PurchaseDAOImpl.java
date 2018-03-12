package dao;

import entity.Purchase;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public class PurchaseDAOImpl implements PurchaseDAO {

    private Session session;

    public PurchaseDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public Purchase get(long id) {
        return session.get(Purchase.class, id);
    }

    @Override
    public List<Purchase> getAll() {
        return session.createQuery("from Purchase", Purchase.class).list();
    }

    @Override
    public long create(Purchase purchase) {
        return (Long) session.save(purchase);
    }

}
