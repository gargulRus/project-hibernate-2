package dao;

import entitys.FilmText;
import org.hibernate.Session;

public class FilmTextDAO extends GenericDao<FilmText>{
    public FilmTextDAO(Session sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
