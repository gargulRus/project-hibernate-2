package dao;

import entitys.Inventory;
import org.hibernate.Session;

public class InventoryDAO extends GenericDao<Inventory> {

    public InventoryDAO(Session sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
