package dao;

import entitys.Payment;
import org.hibernate.Session;

public class PaymentDAO extends GenericDao<Payment> {
    public PaymentDAO(Session sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
