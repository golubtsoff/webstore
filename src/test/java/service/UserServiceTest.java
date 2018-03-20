package service;

import entity.Item;
import entity.Person;
import exception.DBException;
import exception.ServiceException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    private final PersonService personService = new PersonServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = ServiceException.class)
    public void testSetPurchaseDBException() throws Exception {
        Person person = personService.signUp("Bob", "pass345");
        UserService userService = ServiceFactory.getUserService();
        userService.setPurchase(-1, -1, person);
    }

    @Test(expected = DBException.class)
    public void testSetPurchaseServiceException() throws Exception {
        Person person = personService.signUp("Bob", "pass345");
        UserService userService = ServiceFactory.getUserService();
        Item item = new Item("Гравитационный 3D лабиринт",
                "\"Гравитационный 3D-лабиринт\" - объемная головоломка от компании ThinkFun, увидевшая свет в 2017 году. Решая ее задания, игроку предстоит проложить маршрут для металлического шарика, который скатывается под собственным весом по выстраиваемому лабиринту.\n" +
                        "\n" +
                        "      Игра-головоломка \"Гравитационный 3D Лабиринт\", в основе которой Закон всемирного тяготения, несомненно, станет достойным испытанием для ваших умственных способностей и пространственного воображения. ",
                new BigDecimal(3290),
                0);

        Long itemId = adminService.createItem(item);
        userService.setPurchase(itemId, 1, person);
    }

    @Test
    public void testSetPurchase() throws InterruptedException, DBException {
        final int COUNT_USER = 10;
        final int AMOUNT_ITEM = 5;
        final AtomicInteger countPurchase = new AtomicInteger(0);

        PersonService personService = new PersonServiceImpl();
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < COUNT_USER; i++) {
            persons.add(personService.signUp("user100" + i, "user100" + i));
        }
        Item item = new Item("Головоломка Кубик Рубика 4*4 New",
                "Головоломка \"Кубик Рубика 4*4 New\"- усложненный вариант классической головоломки, покорившей весь мир. У него уже по 16 клеток на каждой грани и к тому же совершенно иные алгоритмы сборки. Кстати, в оригинале этот кубик называется Rubik’s Revenge, или Месть Рубика.",
                new BigDecimal(10),
                AMOUNT_ITEM);
        AdminService adminService = new AdminServiceImpl();
        final Long itemId = adminService.createItem(item);

        List<Thread> threads = new ArrayList<>();
        for (final Person person : persons) {
//            threads.add(new Thread(() -> {
//                UserService userService = ServiceFactory.getUserService();
//                try {
//                    userService.setPurchase(itemId, 1, person);
//                    countPurchase.incrementAndGet();
//                } catch (DBException | ServiceException e) {
//                    e.printStackTrace();
//                }
//            }));
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    UserService userService = ServiceFactory.getUserService();
                    try {
                        userService.setPurchase(itemId, 1, person);
                        countPurchase.incrementAndGet();
                    } catch (DBException | ServiceException e) {
                        e.printStackTrace();
                    }
                }
            };
            threads.add(new Thread(runnable));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals("Count of purchases and count of item not equals",
                AMOUNT_ITEM,
                adminService.getPurchases().size());
    }

}