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

    ItemDAOImpl() {
    }

    @Override
    public Item get(long id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(Item.class, id, LockMode.PESSIMISTIC_READ);
    }

    @Override
    public List<Item> getAll() {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("from Item", Item.class)
                .list();
    }

    @Override
    public long create(Item item) {
        return (Long) DBService.getSessionFactory()
                .getCurrentSession()
                .save(item);
    }

    @Override
    public void update(Item item) {
        DBService.getSessionFactory().getCurrentSession()
                .update(item);
    }

    @Override
    public Item delete(long id) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        Item item = session.byId(Item.class).load(id);
        session.delete(item);
        return item;
    }

    @Override
    public void deleteAll(){
        DBService.getSessionFactory()
                .getCurrentSession()
                .createQuery("delete from Item")
                .executeUpdate();
    }
}
