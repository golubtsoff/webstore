package service;

import dao.*;
import entity.Item;
import entity.Person;
import entity.Role;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
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

    private static Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());

    @Override
    public Person signIn(String name, String password) throws DBException {
        Session session = DBService.getSessionFactory().getCurrentSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            PersonDAO dao = DaoFactory.getPersonDAO();
            Person person = dao.getByName(name);
            if (person == null || !person.getPassword().equals(password)){
                return null;
            }

            transaction.commit();
            logger.fine("Person signed: " + person);
            return person;
        } catch (HibernateException | NoResultException e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            throw new DBException(e);
        }
    }

    @Override
    public Person signUp(String name, String password) throws DBException {
        return this.signUp(name, password, Role.user);
    }

    @Override
    public Person signUp(String name, String password, Role role) throws DBException {
        Session session = DBService.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }

            PersonDAO dao = DaoFactory.getPersonDAO();
            Long id = dao.create(new Person(name, password, role));
            Person person = dao.get(id);

            transaction.commit();

            logger.fine("Person registered: " + person);

            return person;
        } catch (HibernateException | NoResultException e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            throw new DBException(e);
        }
    }

    @Override
    public Item getItem(long id) throws DBException {
        Session session = DBService.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }

            ItemDAO dao = DaoFactory.getItemDAO();
            Item item = dao.get(id);

            transaction.commit();
            return item;

        } catch (HibernateException | NoResultException e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            throw new DBException(e);
        }
    }

    @Override
    public List<Item> getItems(Person person) throws DBException {
        Session session = DBService.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            ItemDAO dao = DaoFactory.getItemDAO();
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
        } catch (HibernateException | NoResultException e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
            throw new DBException(e);
        }
    }
}