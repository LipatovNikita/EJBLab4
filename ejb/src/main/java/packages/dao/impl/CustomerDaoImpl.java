package packages.dao.impl;

import packages.dao.CustomerDao;
import packages.entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext(unitName = "lab4ejb")
    EntityManager entityManager;

    public void edit(Customer customer) {
        entityManager.merge(customer);
    }

    public List<Customer> getCustomers() {
        CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
        criteria.select(criteria.from(Customer.class));
        return entityManager.createQuery(criteria).getResultList();
    }
}
