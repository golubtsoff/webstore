package service;

import static data.TestData.*;
import static org.junit.Assert.*;

import entity.Item;
import entity.Person;
import exception.DBException;
import org.junit.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonServiceTest extends ServiceTest {

    @Before
    public void setUp() throws Exception {
        initData();
    }

    @Test
    public void testSignIn() throws Exception {
        assert personService != null;
        Person testPerson = personService.signIn(personSigned.getLogin(), personSigned.getPassword());
        assertEquals("Not autorized", personSigned, testPerson);
    }

    @Test(expected = DBException.class)
    public void testSignInException() throws Exception {
        assert personService != null;
        personService.signIn(personNotSigned.getLogin(), personNotSigned.getPassword());
    }

    @Test
    public void testSignUp() throws Exception {
        assert personService != null;
        Person testPerson = personService.signUp(personNotSigned.getLogin(), personNotSigned.getPassword());
        assertEquals("Not registered", personNotSigned, testPerson);
    }

    @Test(expected = DBException.class)
    public void testSignUpException() throws Exception {
        assert personService != null;
        personService.signUp(personSigned.getLogin(), personSigned.getPassword());
    }

    @Test
    public void testGetItem() throws Exception {
        assert personService != null;
        Item testItem = personService.getItem(itemsId.get(0));
        assertEquals("Wrong item", item1, testItem);
    }

    @Test
    public void testGetItemIsNull() throws Exception {
        assert adminService != null;
        adminService.deleteItem(itemsId.get(0));
        assertNull(adminService.getItem(itemsId.get(0)));
    }

    @Test
    public void testGetItems() throws Exception {
        Map<Long, Item> mapItems = new HashMap<>();
        for (Long id : itemsId) {
            assert adminService != null;
            mapItems.put(id, adminService.getItem(id));
        }

        assert personService != null;
        List<Item> testItems = personService.getItems(personSigned);

        assertEquals("Item's lists not equals", testItems.size(), mapItems.size()-1);
        for(Item item : testItems){
            assertEquals("Item and test item not equals", item, mapItems.get(item.getId()));
        }

        testItems = personService.getItems(personAdmin);

        assertEquals("Item's lists not equals", testItems.size(), mapItems.size());
        for(Item item : testItems){
            assertEquals("Item and test item not equals", item, mapItems.get(item.getId()));
        }
    }

}