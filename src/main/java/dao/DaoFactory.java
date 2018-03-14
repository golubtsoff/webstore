package dao;

public class DaoFactory {
    private static volatile ItemDAO itemDAO;
    private static volatile PersonDAO personDAO;
    private static volatile PurchaseDAO purchaseDAO;


    public static ItemDAO getItemDAO() {
        if(itemDAO == null) {
            synchronized(DaoFactory.class) {
                if(itemDAO == null) {
                    itemDAO = new ItemDAOImpl();
                }
            }
        }
        return itemDAO;
    }

    public static PersonDAO getPersonDAO() {
        if(personDAO == null) {
            synchronized(DaoFactory.class) {
                if(personDAO == null) {
                    personDAO = new PersonDAOImpl();
                }
            }
        }
        return personDAO;
    }

    public static PurchaseDAO getPurchaseDAO() {
        if(purchaseDAO == null) {
            synchronized(DaoFactory.class) {
                if(purchaseDAO == null) {
                    purchaseDAO = new PurchaseDAOImpl();
                }
            }
        }
        return purchaseDAO;
    }
}