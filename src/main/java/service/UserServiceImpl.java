package service;

import dao.*;
import entity.Item;
import entity.Person;
import entity.Purchase;
import exception.DBException;
import exception.ServiceException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public UserServiceImpl(Person person){
        this.person = person;
    }

    @Override
    public long setPurchase(long itemId, int amount) throws DBException, ServiceException {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            ItemDAO itemDAO = new ItemDAOImpl(session);
            Item item = itemDAO.get(itemId);
            checkConditionPurchase(item, amount);
            Purchase purchase = new Purchase(person, item, LocalDateTime.now(), amount);
            PurchaseDAO purchaseDAO = new PurchaseDAOImpl(session);
            Long purchaseId = purchaseDAO.create(purchase);
            purchase = purchaseDAO.get(purchaseId);

            item.setAmount(item.getAmount() - amount);
            itemDAO.update(item);

            transaction.commit();

            logger.fine("Item purchased: " + purchase);
            return purchaseId;
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        } catch (ServiceException el){
            throw el;
        }
    }

    private void checkConditionPurchase(Item item, int amount) throws ServiceException{
        if (item == null){
            throw new ServiceException("There is no item");
        } else if (amount > item.getAmount()){
            throw new ServiceException("Not enough amount of items");
        }
    }
}
