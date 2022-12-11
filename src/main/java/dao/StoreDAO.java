package dao;

import entitys.Store;
import org.hibernate.Session;

public class StoreDAO extends GenericDao<Store> {
    public StoreDAO(Session sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
