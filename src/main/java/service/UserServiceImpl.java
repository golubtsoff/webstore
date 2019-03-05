package service;

import dao.*;
import entity.Item;
import entity.Person;
import entity.Purchase;
import exception.DBException;
import exception.ServiceException;
import org.hibernate.*;
import util.DBService;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */

public class UserServiceImpl extends PersonServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    UserServiceImpl() {
    }

    @Override
    public void setPurchase(long itemId, int amount, Person person) throws DBException, ServiceException {
        Transaction transaction = DBService.getTransaction();
        try {
            ItemDAO itemDAO = DaoFactory.getDao(ItemDAO.class);
            assert itemDAO != null;
            Item item = itemDAO.get(itemId);

            if (!checkConditionPurchase(item, amount)) {
                DBService.transactionRollback(transaction);
                throw new ServiceException("No item for purchase");
            }

            Purchase purchase = new Purchase(person, item, LocalDateTime.now(), amount);
            PurchaseDAO purchaseDAO = DaoFactory.getDao(PurchaseDAO.class);
            assert purchaseDAO != null;
            Long purchaseId = purchaseDAO.create(purchase);
            purchase = purchaseDAO.get(purchaseId);
            item.setAmount(item.getAmount() - amount);

            transaction.commit();

            logger.fine("Item purchased: " + purchase);
        } catch (HibernateException | NoResultException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        } catch (NullPointerException | IllegalStateException el) {
            DBService.transactionRollback(transaction);
            throw new ServiceException(el);
        }
    }

    private boolean checkConditionPurchase(Item item, int amount) {
        return item != null && amount <= item.getAmount();
    }
}
