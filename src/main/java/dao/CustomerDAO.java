package dao;

import entitys.Customer;
import org.hibernate.Session;

public class CustomerDAO extends GenericDao<Customer> {

    public CustomerDAO(Session sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
