package dao;

import entity.Person;
import org.hibernate.Session;
import util.DBService;

import javax.persistence.Query;
import javax.persistence.criteria.*;

/**
 * Created by Evgeniy Golubtsov on 11.02.2018.
 */
public class PersonDAOImpl implements PersonDAO {

    PersonDAOImpl() {
    }

    @Override
    public Person get(long id) {
        return DBService.getSessionFactory()
                .getCurrentSession()
                .get(Person.class, id);
    }

    @Override
    public Person getByName(String login) {
        Session session = DBService.getSessionFactory().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        Query query = session.createQuery("from Person where login = :paramLogin");
        query.setParameter("paramLogin", login);
        return (Person) query.getSingleResult();
    }

    @Override
    public long create(Person person) {
        return (Long) DBService.getSessionFactory()
                .getCurrentSession()
                .save(person);
    }

}
