package lesson3;

import lesson3.entity.Class1;
import lesson3.entity.Class2;
import lesson3.entity.Class3;
import lesson3.entity.User;
import lesson3.util.EMUtil;
import lesson3.util.SFUtil;
import org.hibernate.Session;
import org.junit.Test;

import javax.persistence.EntityManager;

import static lesson3.services.Generations.Gen;

public class UserTest {
    @Test
    public void Test() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User user = new User(2, "fs", "fds", 200.0);
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.getTransaction().begin();
        User userFromDb = entityManager.find(User.class, 1);
        userFromDb.setPassword("123");
        entityManager.getTransaction().commit();
    }

    @Test
    public void TestGen() {
       /*Gen();*/
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Class1 class1 = new Class1();
        Class2 class2 = new Class2();
        Class3 class3 = new Class3();
        entityManager.persist(class1);
        entityManager.persist(class2);
        entityManager.persist(class3);
        entityManager.getTransaction().commit();
    }

    @Test
    public void GetIdTest() {
        Session session = SFUtil.getSession();
        session.getTransaction().begin();
        Class1 class1 = new Class1();
        Class2 class2 = new Class2();
        Class3 class3 = new Class3();
        session.persist(class1);
        session.persist(class2);
        session.persist(class3);
        session.getTransaction().commit();
        session.clear();
        session.getTransaction().begin();
        session.merge(Class1.class);
        session.merge(Class2.class);
        session.merge(Class3.class);
        System.out.println("GenerationType.AUTO" + session.getIdentifier(class1));
        System.out.println("GenerationType.IDENTITY" + session.getIdentifier(class2));
        System.out.println("GenerationType.TABLE" + session.getIdentifier(class3));
        session.getTransaction().commit();
    }
}
