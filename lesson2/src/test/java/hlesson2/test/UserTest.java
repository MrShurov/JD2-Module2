package hlesson2.test;

import hlesson2.entity.User;
import hlesson2.util.EMUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

import static hlesson2.services.UserDao.CreateUser;
import static hlesson2.services.UserService.getUser;
import static hlesson2.services.UserService.loadUser;

public class UserTest {

/*    @Test
    public void getUserTest(){
        Assert.assertNotNull(getUser(2));
    }

    @Test
    public void loadUserTest(){
        Assert.assertNotNull(loadUser(2));
    }*/

    @Test
    public void createTest() {
        User user = new User(4,"qw","21");
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Test
    public void readTest(){
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        User user = new User(1,"qw","21");
        em.getTransaction().commit();
        em.clear();
        em.getTransaction().begin();
        User user2 = em.find(User.class, 1);
        em.getTransaction().commit();
    }

    @Test
    public void updateTest() {
        User user = new User(5,"qw","21");
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
        User user = new User(6,"qw","21");
        em.getTransaction().begin();
        em.persist(user);
        em.remove(user);
        em.getTransaction().commit();
    }
}
