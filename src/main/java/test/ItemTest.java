package test;

import dao.DBService;
import dao.ItemDAO;
import dao.ItemDAOImpl;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 10.02.2018.
 */
public class ItemTest {
    public static void main(String[] args) throws Exception {
        try (Session session = DBService.getSession()){
            Transaction transaction = session.beginTransaction();

            ItemDAO dao = new ItemDAOImpl(session);
            Item i1 = new Item("Самолёт", "Модель на радиоуправлении", 1000, 5);
            Item i2 = new Item("Танк", "Конструктор для сборки танка", 999.99, 3);
            long id1 = dao.create(i1);
            long id2 = dao.create(i2);
            Item i3 = dao.get(id1);
            System.out.println(i3);
            i3.setPrice(2000);
            dao.update(i3);
            System.out.println(i3);
            List<Item> items = dao.getAll();
            System.out.println(items);
            dao.delete(i3.getId());
            List<Item> items2 = dao.getAll();
            System.out.println(items2);

            i1.setTitle("Корабль");
            Item i4 = i1;
//            Item i4 = new Item("Машина", "Конструктор для сборки танка", 999.99, 3);

            dao.create(i4);
            List<Item> items3 = dao.getAll();
            System.out.println(items3);

            transaction.commit();
        } finally {
            DBService.close();
        }

    }
}
