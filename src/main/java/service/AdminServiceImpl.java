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

    static {
//        Logger globalLogger = LogManager.getLogManager().getLogger("");
//        globalLogger.setLevel(Level.INFO);
//        for (Handler handler : globalLogger.getHandlers()) {
//            handler.setLevel(Level.FINE);
//        }
//
//        Logger globalLogger = Logger.getLogger("");
//        Handler[] handlers = globalLogger.getHandlers();
//        for(Handler handler : handlers) {
//            globalLogger.removeHandler(handler);
//        }

//        LogManager.getLogManager().reset();
//        Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
//        globalLogger.setLevel(Level.OFF);

        try {
            logger.setUseParentHandlers(false);
            logger.addHandler(new FileHandler("webstore.log", 1_000_000, 10, true));
            logger.setLevel(Level.FINE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AdminServiceImpl(){
    }

    @Override
    public long createItem(Item item) {
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
