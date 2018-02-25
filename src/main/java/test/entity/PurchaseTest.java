package test.entity;

import dao.*;
import entity.Item;
import entity.Person;
import entity.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DBService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static entity.Role.user;

/**
 * Created by Evgeniy Golubtsov on 10.02.2018.
 */
public class PurchaseTest {
    public static void main(String[] args) throws Exception {
        try (Session session = DBService.getSession()) {
            Transaction transaction = session.beginTransaction();

            PurchaseDAO purchaseDAO = new PurchaseDAOImpl(session);
            PersonDAO personDAO = new PersonDAOImpl(session);
            ItemDAO itemDAO = new ItemDAOImpl(session);

            Person p1 = new Person("Пётр", "pass345", user);
            personDAO.create(p1);
            p1 = personDAO.get(1L);

            Item i1 = new Item("Самолёт", "Модель на радиоуправлении", new BigDecimal(1000), 5);
            itemDAO.create(i1);
            i1 = itemDAO.get(1);

            Purchase pr1 = new Purchase(p1, i1, LocalDateTime.now(), 2);
            purchaseDAO.create(pr1);
            Purchase pr2 = purchaseDAO.get(1L);
            System.out.println(pr2);
            List<Purchase> prs = purchaseDAO.getAll();
            System.out.println(prs);

            transaction.commit();
        } finally {
            DBService.close();
        }
    }
}
