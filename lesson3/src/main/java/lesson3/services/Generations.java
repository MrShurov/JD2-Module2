package lesson3.services;

import lesson3.entity.Class1;
import lesson3.entity.User;
import lesson3.util.EMUtil;
import lesson3.util.SFUtil;
import org.hibernate.Session;

public class Generations {
    public static void Gen(){
        Session session = SFUtil.getSession();
        session.getTransaction().begin();
        User user = session.load(User.class,1);
        System.out.println(session.getIdentifier(user));
    }
}
