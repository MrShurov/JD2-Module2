package lesson3;

import lesson3.entity.User;
import lesson3.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

import static lesson3.services.Generations.Gen;

public class UserTest {
    @Test
    public void Test(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User user = new User(2,"fs","fds",200.0);
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.getTransaction().begin();
        User userFromDb = entityManager.find(User.class,1);
        userFromDb.setPassword("123");
        entityManager.getTransaction().commit();
    }

    @Test
    public void TestGen(){
        Gen();
    }
}
