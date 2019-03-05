package dao;

import java.util.Arrays;
import java.util.List;

public abstract class DaoFactory {

    private static final List<Dao> daoList = Arrays.asList(
            new ItemDAOImpl(),
            new PersonDAOImpl(),
            new PurchaseDAOImpl()
    );

    public static <T extends Dao> T getDao(Class<T> cl){
        for(Dao dao : daoList)
            if (cl.isInstance(dao))
                return cl.cast(dao);
        return null;
    }
}