package service;

import entity.Person;
import exception.DBException;
import exception.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static data.TestData.*;
import static org.junit.Assert.assertEquals;

public class UserServiceTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        initData();
    }

    @Test (expected = ServiceException.class)
    public void testSetPurchaseDBException() throws Exception {
        assert userService != null;
        userService.setPurchase(-1, -1, personSigned);
    }

    @Test (expected = ServiceException.class)
    public void testSetPurchaseServiceException() throws Exception {
        assert userService != null;
        userService.setPurchase(itemsId.get(2), 1, personSigned);
    }

    @Test
    public void testSetPurchase() throws InterruptedException, DBException {
        final int AMOUNT_ITEM = item4.getAmount();
        final int COUNT_USER = AMOUNT_ITEM + 10;

        AtomicInteger countPurchase = new AtomicInteger(0);

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < COUNT_USER; i++) {
            assert personService != null;
            persons.add(personService.signUp("user100" + i, "user100" + i));
        }

        assert adminService != null;
        Long itemId = adminService.createItem(item4);

        List<Thread> threads = new ArrayList<>();
        for (Person person : persons){
            threads.add(new Thread(() -> {
                try {
                    assert userService != null;
                    userService.setPurchase(itemId, 1, person);
                    countPurchase.incrementAndGet();
                } catch (DBException | ServiceException e) {
                    e.printStackTrace();
                }
            }));
        }

        for (Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads){
            thread.join();
        }

        assertEquals("Count of purchases and count of item not equals",
                AMOUNT_ITEM,
                adminService.getPurchases().size());
    }

}