package service;

import dao.*;
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

public class PersonServiceImpl implements PersonService {

    @Override
    public Person signIn(String name, String password) {
        try (Session session = DBService.getSession()){
            PersonDAO dao = new PersonDAOImpl(session);
            return dao.getByName(name);
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }

    @Override
    public Person signUp(String name, String password) {
        return this.signUp(name, password, Role.user);
    }

    @Override
    public Person signUp(String name, String password, Role role) {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            PersonDAO dao = new PersonDAOImpl(session);
            Long id = dao.create(new Person(name, password, role));
            Person person = dao.get(id);

            transaction.commit();

            return person;
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }


    @Override
    public List<Item> getItems() {
        try (Session session = DBService.getSession()){
            ItemDAO dao = new ItemDAOImpl(session);
            return dao.getAll();
        } catch (HibernateException | NoResultException e) {
            throw new DBException(e);
        }
    }
}