package service;

import dao.*;
import entity.Item;
import entity.Person;
import entity.Purchase;
import exception.DBException;
import exception.ServiceException;
import org.hibernate.*;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import util.DBService;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */

public class UserServiceImpl extends PersonServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private Person person;

    public UserServiceImpl(Person person) {
        this.person = person;
    }

    @Override
    public long setPurchase(long itemId, int amount) throws DBException, ServiceException {
        Session session = DBService.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            ItemDAO itemDAO = new ItemDAOImpl();
            Item item = itemDAO.get(itemId);

            if (!checkConditionPurchase(item, amount)) {
                if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                        || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                    session.getTransaction().rollback();
                }
                throw new ServiceException("No item for purchase");
            }

            Purchase purchase = new Purchase(person, item, LocalDateTime.now(), amount);
            PurchaseDAO purchaseDAO = new PurchaseDAOImpl();
            Long purchaseId = purchaseDAO.create(purchase);
            purchase = purchaseDAO.get(purchaseId);
            item.setAmount(item.getAmount() - amount);

            session.getTransaction().commit();

            logger.fine("Item purchased: " + purchase);
            return purchaseId;
        } catch (HibernateException | NoResultException e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            throw new DBException(e);
        } catch (NullPointerException | IllegalStateException el) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            throw new ServiceException(el);
        }
    }

    private boolean checkConditionPurchase(Item item, int amount) {
        return item != null && amount <= item.getAmount();
    }
}
