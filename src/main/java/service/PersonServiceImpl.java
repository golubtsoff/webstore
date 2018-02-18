package service;

import dao.DBService;
import dao.PersonDAO;
import dao.PersonDAOImpl;
import entity.Item;
import entity.Person;
import entity.Role;
import exception.DBException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 18.02.2018.
 */
//TODO implement methods
public class PersonServiceImpl implements PersonService {

    protected Person person;

    @Override
    public Person signIn(String name, String password) {
        try (Session session = DBService.getSession()){
            PersonDAO dao = new PersonDAOImpl(session);
            person = dao.getByName(name);
            return person;
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Person signUp(String name, String password) {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            PersonDAO dao = new PersonDAOImpl(session);
            Long id = dao.create(new Person(name, password, Role.user));
            Person person = dao.get(id);

            transaction.commit();

            return person;
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }


    @Override
    public List<Item> getItems() {
        return null;
    }
}
