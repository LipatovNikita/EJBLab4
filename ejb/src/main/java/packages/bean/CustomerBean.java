package packages.bean;

import lombok.Data;
import packages.dao.CustomerDao;
import packages.entity.Customer;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Data
@Named
@RequestScoped
public class CustomerBean {

    @EJB
    CustomerDao customerDao;

    public List<Customer> getAllCustomers() {
        return customerDao.getCustomers();
    }
}
