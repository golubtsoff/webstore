package dao;

import org.junit.Assert;
import org.junit.Test;

public class DaoFactoryTest {

    @Test
    public void getItemDao() {
        ItemDAO dao = DaoFactory.getDao(ItemDAO.class);
        Assert.assertTrue(ItemDAO.class.isInstance(dao));
    }

    @Test
    public void getPersonDao() {
        PersonDAO dao = DaoFactory.getDao(PersonDAO.class);
        Assert.assertTrue(PersonDAO.class.isInstance(dao));
    }

    @Test
    public void getPurchaseDao() {
        PurchaseDAO dao = DaoFactory.getDao(PurchaseDAO.class);
        Assert.assertTrue(PurchaseDAO.class.isInstance(dao));
    }
}