package dao;

import entity.Person;

/**
 * Created by Evgeniy Golubtsov on 10.02.2018.
 */
public interface PersonDAO extends Dao{

    Person get(long id);

    Person getByName(String login);

    long create(Person account);

    void deleteAll();
}
