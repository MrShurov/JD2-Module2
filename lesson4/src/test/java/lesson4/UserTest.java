package lesson4;

import lesson4.entity.InfoUser;
import lesson4.entity.User;
import lesson4.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class UserTest {
    @Test
    public void TableTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User user = new User(null,"Sykpyn","sads",null);
        user.setAddress(new InfoUser("asd","sd","2130000"));
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
}
