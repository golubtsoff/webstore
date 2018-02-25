package service;

import dao.*;
import entity.Item;
import entity.Purchase;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */

public class AdminServiceImpl extends PersonServiceImpl implements AdminService {

    public AdminServiceImpl(){
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
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            ItemDAO dao = new ItemDAOImpl(session);
            dao.update(item);

            transaction.commit();
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
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
        try (Session session = DBService.getSession()){
            PurchaseDAO dao = new PurchaseDAOImpl(session);
            return dao.getAll();
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }
}
