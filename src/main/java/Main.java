import config.MySessionFactory;
import entitys.City;
import entitys.Country;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.lang.reflect.Array;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try(Session session = MySessionFactory.getSessionFactory().openSession()) {

//            List<Country> countryArray = session.createQuery("from Country ", Country.class).getResultList();
            List<City> cityArray = session.createQuery("from City ", City.class).getResultList();

            for(City city : cityArray) {
                System.out.println(city.getCountry().getLast_update());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
