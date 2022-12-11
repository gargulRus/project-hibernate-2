package dao;

import entitys.Staff;
import org.hibernate.Session;

public class StaffDAO extends GenericDao<Staff> {
    public StaffDAO(Session sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
