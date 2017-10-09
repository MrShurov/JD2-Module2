package hlesson2.test;

import hlesson2.entity.User;
import hlesson2.util.SFUtil;
import org.hibernate.Session;
import org.junit.Test;

public class Flush {
    @Test
    public void flushUserTest() {
        Session session = SFUtil.getSession();
        session.getTransaction().begin();
        for (int i = 0; i < 10000; i++) {
            User user = new User(i, "q", "q");
            session.save(user);
            if (i % 10 == 0) {
                session.flush();
                session.clear();
                System.out.println("Запрос");
            }
        }
        session.getTransaction().commit();
        session.close();
    }
}
