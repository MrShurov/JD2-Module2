package lesson1;

import lesson1.pojos.User;
import lesson1.util.EMUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

public class UserTest {
    @Test
    public void readTest(){
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, 1);
        em.getTransaction().commit();
    }

    @Test
    public void createTest() {
        User user = new User(null,"Egor","1234","ADMIN","ACTIVE",100.0);
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Test
    public void updateTest() {
        User user = new User(null,"Egor","1234","ADMIN","ACTIVE",100.0);
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        user.setUsername("Demo");
        em.getTransaction().commit();
        Assert.assertEquals("Demo",user.getUsername());
    }

    @Test
    public void deleteTest() {
        EntityManager em = EMUtil.getEntityManager();
        User user = new User(null,"DELETE","1234","ADMIN","ACTIVE",100.0);
        em.getTransaction().begin();
        em.persist(user);
        em.remove(user);
        em.getTransaction().commit();
    }
}
