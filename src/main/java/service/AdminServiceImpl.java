package service;

import dao.*;
import entity.Item;
import entity.Purchase;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.logging.*;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */

public class AdminServiceImpl extends PersonServiceImpl implements AdminService {

    private static final Logger logger = Logger.getLogger(AdminServiceImpl.class.getName());

    AdminServiceImpl() {
    }

    public long createItem(Item item) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ItemDAO dao = DaoFactory.getItemDAO();
            long id = dao.create(item);

            transaction.commit();

            logger.fine("Create item " + item);

            return id;
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    public void updateItem(Item item) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ItemDAO dao = DaoFactory.getItemDAO();
            dao.update(item);

            transaction.commit();

            logger.fine("Update item " + item);
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    public void deleteItem(long id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ItemDAO dao = DaoFactory.getItemDAO();
            Item item = dao.delete(id);

            transaction.commit();

            logger.fine("Delete item " + item);
        } catch (HibernateException | NoResultException | IllegalArgumentException | IllegalStateException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    public List<Purchase> getPurchases() throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            PurchaseDAO dao = DaoFactory.getPurchaseDAO();
            List<Purchase> purchases = dao.getAll();
            transaction.commit();
            return purchases;
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }
}
