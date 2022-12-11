package dao;

import entitys.Actor;
import org.hibernate.Session;

public class ActorDAO extends GenericDao<Actor> {
    public ActorDAO(Session sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
