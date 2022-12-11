package dao;

import entitys.Address;
import org.hibernate.Session;

public class AddressDAO extends GenericDao<Address> {
    public AddressDAO(Session sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
