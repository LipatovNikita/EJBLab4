package packages.bean;

import lombok.Data;
import packages.dao.ItemDao;
import packages.entity.Item;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Data
@Named
@SessionScoped
public class ItemBean implements Serializable {

    private List<Item> items;

    @EJB
    ItemDao itemDao;

    public String getItemsByCustomer(int id) {
        items = itemDao.getItemsByCustomer(id);
        return "items";
    }
}
