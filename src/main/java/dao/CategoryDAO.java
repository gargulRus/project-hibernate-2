package dao;

import entitys.Category;
import org.hibernate.Session;

public class CategoryDAO extends GenericDao<Category> {
    public CategoryDAO(Session sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
