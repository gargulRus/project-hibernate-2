package dao;

import entitys.Country;
import org.hibernate.Session;

public class CountryDAO extends GenericDao<Country> {
    public CountryDAO(Session sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
