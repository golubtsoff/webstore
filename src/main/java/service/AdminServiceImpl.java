package service;

import dao.*;
import entity.Item;
import entity.Person;
import entity.Purchase;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */

//TODO implement methods
public class AdminServiceImpl extends PersonServiceImpl implements AdminService {

    private Person person;

    public AdminServiceImpl(Person person){
        this.person = person;
    }

    @Override
    public long createItem(Item item) {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            ItemDAO dao = new ItemDAOImpl(session);
            long id = dao.create(item);

            transaction.commit();
            return id;
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void deleteItem(long id) {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            ItemDAO dao = new ItemDAOImpl(session);
            dao.delete(id);

            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Purchase> getPurchases() {
        return null;
    }
}
