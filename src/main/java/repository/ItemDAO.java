package repository;

import model.Item;

public interface ItemDAO {
   Item findItemById(long id);

}
