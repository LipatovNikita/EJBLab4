package packages.dao;

import packages.entity.Item;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ItemDao {
    void edit(Item item);
    List<Item> getItemsByCustomer(int id_customer);
    Item getItemById(int id);
}
