package lesson3.util;

import lesson3.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;


public class SFUtil {
    private static final SessionFactory sessionFactory;

    static {
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/lesson3");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "2372002Java");
        settings.put(Environment.HBM2DDL_AUTO, "create");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.STORAGE_ENGINE, "innodb");
        serviceRegistryBuilder.applySettings(settings);
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        MetadataSources sources = new MetadataSources(serviceRegistry);
        sources.addAnnotatedClass(User.class);
        Metadata metadata = sources.getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
