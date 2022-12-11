package dao;

import entitys.Film;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class FilmDAO extends GenericDao<Film> {

    public FilmDAO(Session sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFirstAvialabaleFilmToRent() {
        Query<Film> query = getCurrentSession().createQuery("select f from Film  f " +
                "where f.filmId not in(select distinct film.filmId from Inventory)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
