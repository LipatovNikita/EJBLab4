package packages.dao.impl;

import packages.dao.ItemDao;
import packages.entity.Item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ItemDaoImpl implements ItemDao {

    @PersistenceContext(unitName = "lab4ejb")
    EntityManager entityManager;

    public void edit(Item item) {
        entityManager.merge(item);
    }

    public List<Item> getItemsByCustomer(int id_customer) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root model = criteriaQuery.from(Item.class);
        criteriaQuery.where(criteriaBuilder.equal(model.get("customer"), id_customer));
        TypedQuery query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Item getItemById(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root model = criteriaQuery.from(Item.class);
        criteriaQuery.where(criteriaBuilder.equal(model.get("id"), id));
        TypedQuery query = entityManager.createQuery(criteriaQuery);
        return (Item) query.getSingleResult();
    }
}
