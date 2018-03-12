package dao;

import entity.Person;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.*;

/**
 * Created by Evgeniy Golubtsov on 11.02.2018.
 */
public class PersonDAOImpl implements PersonDAO {

    private Session session;

    public PersonDAOImpl(Session session) {
        this.session = session;
    }

    @Override
    public Person get(long id) {
        return session.get(Person.class, id);
    }

    @Override
    public Person getByName(String login) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Query query = session.createQuery("from Person where login = :paramLogin");
        query.setParameter("paramLogin", login);
        return (Person) query.getSingleResult();
    }

    @Override
    public long create(Person person) {
        return (Long) session.save(person);
    }

}
