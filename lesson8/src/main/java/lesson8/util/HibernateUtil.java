package lesson8.util;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory emFactory;
    private static final SessionFactory sessionFactory=null;

    /*
        EntityManager initialization
     */
    static {
        emFactory = Persistence.createEntityManagerFactory("lesson8");
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    public static void close() {
        if (emFactory != null) {
            emFactory.close();
        }
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
