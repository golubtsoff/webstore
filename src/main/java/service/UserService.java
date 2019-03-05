package service;

import entity.Person;
import exception.DBException;
import exception.ServiceException;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public interface UserService extends PersonService {

    void setPurchase(long itemId, int amount, Person person) throws DBException, ServiceException;
}
