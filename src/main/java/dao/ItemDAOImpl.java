package dao;

import entity.Item;
import org.hibernate.LockMode;
import org.hibernate.Session;
import util.DBService;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 11.02.2018.
 */
public class ItemDAOImpl implements ItemDAO {

    @Override
    public Item get(long id) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        return session.get(Item.class, id, LockMode.PESSIMISTIC_READ);
    }

    @Override
    public List<Item> getAll() {
        Session session = DBService.getSessionFactory().getCurrentSession();
        return session.createQuery("from Item", Item.class).list();
    }

    @Override
    public long create(Item item) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        return (Long) session.save(item);
    }

    @Override
    public void update(Item item) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        session.update(item);
    }

    @Override
    public Item delete(long id) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        Item item = session.byId(Item.class).load(id);
        session.delete(item);
        return item;
    }
}
