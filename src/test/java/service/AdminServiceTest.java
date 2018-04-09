package service;

import entity.Item;
import entity.Purchase;
import exception.DBException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static data.TestData.*;
import static org.junit.Assert.*;

// TODO make all tests independent
public class AdminServiceTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        initData();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateItem() throws Exception {
        Long itemId = adminService.createItem(item4);
        Item testItem = personService.getItem(itemId);
        assertEquals("Wrong create item", item4, testItem);
    }

    @Test(expected = DBException.class)
    public void testCreateItemException() throws Exception {
        adminService.createItem(item1);
    }

    @Test
    public void testUpdateItem() throws Exception {
        Long itemId = adminService.createItem(item4);

        item4.setAmount(item4.getAmount() + 1);
        adminService.updateItem(item4);

        Item testItem = personService.getItem(itemId);
        assertEquals("Wrong update item", item4, testItem);
    }

    @Test (expected = DBException.class)
    public void testUpdateItemException() throws Exception {
        adminService.updateItem(new Item());
    }

    @Test
    public void testDeleteItem() throws Exception {
        adminService.deleteItem(itemsId.get(0));
        Item testItem = adminService.getItem(itemsId.get(0));
        assertNull("Item not deleted", testItem);
    }

    @Test (expected = DBException.class)
    public void testDeleteItemException() throws Exception {
        adminService.deleteItem(itemsId.get(0));
        adminService.deleteItem(itemsId.get(0));
    }

    @Test
    public void testGetPurchases() throws Exception {
        userService.setPurchase(itemsId.get(0), 1, personSigned);
        userService.setPurchase(itemsId.get(0), 1, personSigned);
        userService.setPurchase(itemsId.get(0), 1, personSigned);

        Item item = personService.getItem(itemsId.get(0));

        List<Purchase> testPurchases = adminService.getPurchases();
        assertEquals("Counts purchases not equals", 3, testPurchases.size());
        for (Purchase purchase : testPurchases){
            assertEquals("Person is not same", purchase.getPerson(), personSigned);
            assertEquals("Item is not same", purchase.getItem(), item);
            assertEquals("Price is not same", purchase.getCost(), item.getPrice());
            assertEquals("Item is not same", purchase.getAmount(), 1);
        }
    }

}