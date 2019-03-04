package dao;

import entity.Item;

import java.util.List;

/**
 * Created by Evgeniy Golubtsov on 10.02.2018.
 */
public interface ItemDAO extends Dao{

    Item get(long id);

    List<Item> getAll();

    long create(Item item);

    void update(Item item);

    Item delete(long id);

    void deleteAll();
}
