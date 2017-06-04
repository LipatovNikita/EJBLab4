package packages.dao;

import packages.entity.Customer;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerDao {
    List<Customer> getCustomers();
}
