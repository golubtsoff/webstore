package dao;

import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 11.02.2018.
 */
public class ItemDAOImpl implements ItemDAO {

    private Session session;

    public ItemDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public Item get(long id) {
        return session.get(Item.class, id);
    }

    @Override
    public List<Item> getAll() {
        return session.createQuery("from Item").list();
    }

    @Override
    public long create(Item item) {
        return (Long) session.save(item);
    }

    @Override
    public void update(Item item) {
        session.update(item);
    }

    @Override
    public Item delete(long id) {
        Item item = session.byId(Item.class).load(id);
        session.delete(item);
        return item;
    }
}
