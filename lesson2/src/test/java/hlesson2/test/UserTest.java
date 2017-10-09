package hlesson2.test;

import hlesson2.entity.User;
import hlesson2.util.EMUtil;
import hlesson2.util.SFUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

import static hlesson2.services.UserDao.CreateUser;
import static hlesson2.services.UserService.getUser;
import static hlesson2.services.UserService.loadUser;

public class UserTest {

    @Test
    public void createTest() {
        User user = new User(4,"qw","21");
        EntityManager em = EMUtil.getEntityManager("hlesson2.test");
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Test
    public void readTest(){
        EntityManager em = EMUtil.getEntityManager("hlesson2.test");
        em.getTransaction().begin();
        User user = new User(5,"eds","ds");
        em.persist(user);
        Assert.assertNotNull(em.find(User.class, 5));
        em.getTransaction().commit();
    }

    @Test
    public void updateTest() {
        User user = new User(5,"qw","21");
        EntityManager em = EMUtil.getEntityManager("hlesson2.test");
        em.getTransaction().begin();
        em.persist(user);
        user.setUsername("Demo");
        em.getTransaction().commit();
        Assert.assertEquals("Demo",user.getUsername());
    }

    @Test
    public void deleteTest() {
        EntityManager em = EMUtil.getEntityManager("hlesson2.test");
        User user = new User(6,"qw","21");
        em.getTransaction().begin();
        em.persist(user);
        em.remove(user);
        em.getTransaction().commit();
    }
}
