package dao;

import entitys.Rental;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RentalDAO extends GenericDao<Rental> {
    public RentalDAO(Session sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getAnyUnreturnedRental() {
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.returnDate is null ", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
