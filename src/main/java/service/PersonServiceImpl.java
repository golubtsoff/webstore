package service;

import dao.*;
import entity.Item;
import entity.Person;
import entity.Role;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import util.DBService;

import javax.persistence.NoResultException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */

public class PersonServiceImpl implements PersonService {

    private static final Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());

    PersonServiceImpl(){}

    @Override
    public Person signIn(String name, String password) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            PersonDAO dao = DaoFactory.getDao(PersonDAO.class);
            assert dao != null;
            Person person = dao.getByName(name);
            if (person == null || !person.getPassword().equals(password)){
                return null;
            }

            transaction.commit();
            logger.fine("Person signed: " + person);
            return person;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public Person signUp(String name, String password) throws DBException {
        return this.signUp(name, password, Role.user);
    }

    @Override
    public Person signUp(String name, String password, Role role) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            PersonDAO dao = DaoFactory.getDao(PersonDAO.class);
            assert dao != null;
            Long id = dao.create(new Person(name, password, role));
            Person person = dao.get(id);

            transaction.commit();

            logger.fine("Person registered: " + person);

            return person;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public Item getItem(long id) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ItemDAO dao = DaoFactory.getDao(ItemDAO.class);
            assert dao != null;
            Item item = dao.get(id);

            transaction.commit();
            return item;

        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }

    @Override
    public List<Item> getItems(Person person) throws DBException {
        Transaction transaction = DBService.getTransaction();
        try {
            ItemDAO dao = DaoFactory.getDao(ItemDAO.class);
            assert dao != null;
            List<Item> items = dao.getAll();
            if (person.getRole() == Role.admin){
                items.sort(Comparator.comparing(Item::getTitle));
            } else {
                items = items.stream()
                        .filter((item) -> item.getAmount() > 0)
                        .sorted(Comparator.comparing(Item::getTitle))
                        .collect(Collectors.toList());
            }
            transaction.commit();
            return items;
        } catch (HibernateException | NoResultException | NullPointerException e) {
            DBService.transactionRollback(transaction);
            throw new DBException(e);
        }
    }
}