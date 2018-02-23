package test.entity;

import dao.*;
import entity.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static entity.Role.admin;
import static entity.Role.user;

/**
 * Created by Evgeniy Golubtsov on 10.02.2018.
 */
public class PersonTest {
    public static void main(String[] args) throws Exception {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            PersonDAO dao = new PersonDAOImpl(session);
            Person p1 = new Person("Иван", "pass123", admin);
            Person p2 = new Person("Пётр", "pass345", user);
            long id1 = dao.create(p1);
            long id2 = dao.create(p2);
            Person p3 = dao.get(id1);
            System.out.println(p3);
            Person p4 = dao.getByName("Пётр");
            System.out.println(p4);

            transaction.commit();
        } finally {
            DBService.close();
        }
    }
}
