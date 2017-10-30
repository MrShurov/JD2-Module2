package lesson6.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMUtil {
    private static EntityManagerFactory emFactory=null;

    public static EntityManager getEntityManager() {
        emFactory  = Persistence.createEntityManagerFactory("lesson6");
        return emFactory.createEntityManager();
    }

    public static EntityManager getEntityManager(String unit) {
        emFactory  = Persistence.createEntityManagerFactory(unit);
        return emFactory.createEntityManager();
    }

    public static void closeEMFactory() {
        emFactory.close();
    }
}
