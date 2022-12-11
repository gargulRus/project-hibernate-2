package dao;

import entitys.City;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CityDAO extends GenericDao<City> {
    public CityDAO(Session sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getByName(String name) {
        Query<City> query = getCurrentSession().createQuery("select c from City c where c.city=:NAME", City.class);
        query.setParameter("NAME", name);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
