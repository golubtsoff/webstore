package service;

import entity.Item;
import entity.Person;
import entity.Purchase;
import exception.DBException;
import exception.ServiceException;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 12.02.2018.
 */
public interface UserService extends PersonService {

    long setPurchase(long itemId, int amount) throws DBException, ServiceException;
}
