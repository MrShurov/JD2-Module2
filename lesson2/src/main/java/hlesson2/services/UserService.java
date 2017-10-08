package hlesson2.services;

import hlesson2.entity.User;
import hlesson2.util.EMUtil;
import hlesson2.util.SFUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;

public class UserService {

    public static User getUser(Integer number) {
        Session session = SFUtil.getSession();
        session.beginTransaction();
        User user = session.get(User.class,number);
        session.getTransaction().commit();
        return user;
    }

    public static User loadUser(int number){
        Session session = SFUtil.getSession();
        session.getTransaction().begin();
        User user = session.load(User.class,number);
        session.getTransaction().commit();
        return user;
    }
}
