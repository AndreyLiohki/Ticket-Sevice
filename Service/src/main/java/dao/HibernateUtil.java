package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateUtil {
    private static final  SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
