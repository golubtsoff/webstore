package service;

import exception.DBException;
import exception.ServiceException;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public interface UserService extends PersonService {

    long setPurchase(long itemId, int amount) throws DBException, ServiceException;
}
