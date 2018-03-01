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
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.*;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */

public class AdminServiceImpl extends PersonServiceImpl implements AdminService {

    private static Logger logger = Logger.getLogger(AdminServiceImpl.class.getName());

    public AdminServiceImpl(){
    }

    @Override
    public long createItem(Item item) throws DBException {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            ItemDAO dao = new ItemDAOImpl(session);
            long id = dao.create(item);

            transaction.commit();

            logger.fine("Create item " + item);

            return id;
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void updateItem(Item item) throws DBException {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            ItemDAO dao = new ItemDAOImpl(session);
            dao.update(item);

            transaction.commit();

            logger.fine("Update item " + item);
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteItem(long id) throws DBException {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            ItemDAO dao = new ItemDAOImpl(session);
            Item item = dao.delete(id);

            transaction.commit();

            logger.fine("Delete item " + item);
        } catch (HibernateException | NoResultException | IllegalArgumentException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<Purchase> getPurchases() throws DBException {
        try (Session session = DBService.getSession()){
            PurchaseDAO dao = new PurchaseDAOImpl(session);
            return dao.getAll();
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }
}
