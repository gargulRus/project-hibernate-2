package dao;

import entitys.Language;
import org.hibernate.Session;

public class LanguageDAO extends GenericDao<Language> {
    public LanguageDAO(Session sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
