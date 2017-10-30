package lesson5;

import lesson5.entity.ManyToMany.Department;
import lesson5.entity.ManyToMany.Meeting;
import lesson5.entity.ManyToMany.Person;
import lesson5.util.EMUtil;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ManyToMany {

    @Before
    public void init(){
        EntityManager em = EMUtil.getEntityManager();
        em.getTransaction().begin();
        Department q = new Department("Q");
        Department b = new Department("B");
        em.persist(q);
        em.persist(b);
        Meeting meeting1 = new Meeting();
        Meeting meeting2 = new Meeting();
        meeting1.setSubject("Subject1");
        meeting2.setSubject("Subject2");
        Person p1 = new Person(null, "A", "RB", q, new ArrayList());
        Person p2 = new Person(null, "S", "RG", b, new ArrayList());
        Person p3 = new Person(null, "D", "RG", q, new ArrayList());
        Person p4 = new Person(null, "F", "RB", b, new ArrayList());
        Person p5 = new Person(null, "G", "RB", q, new ArrayList());
        Person p6 = new Person(null, "H", "RB", b, new ArrayList());
        Person p7 = new Person(null, "J", "GB", q, new ArrayList());
        meeting1.getEmployees().add(p1);
        meeting1.getEmployees().add(p2);
        meeting1.getEmployees().add(p3);
        meeting1.getEmployees().add(p5);
        meeting2.getEmployees().add(p4);
        meeting2.getEmployees().add(p5);
        meeting2.getEmployees().add(p6);
        meeting2.getEmployees().add(p7);
        p1.getMeetings().add(meeting1);
        p2.getMeetings().add(meeting1);
        p3.getMeetings().add(meeting1);
        p4.getMeetings().add(meeting2);
        p5.getMeetings().add(meeting1);
        p5.getMeetings().add(meeting2);
        p7.getMeetings().add(meeting2);
        em.persist(meeting1);
        em.persist(meeting2);
        em.getTransaction().commit();
    }

    @Test
    public void updateTest(){
        EntityManager entityManager = EMUtil.getEntityManager("lesson5.test");
        entityManager.getTransaction().begin();
        Meeting meeting3 = new Meeting();
        Meeting meeting1 = entityManager.find(Meeting.class,1L);
        Person p1 = entityManager.find(Person.class,6L);
        meeting3.setSubject("Subject3");
        List<Meeting> meetings = p1.getMeetings();
        p1.setMeetings(meetings);
        p1.getMeetings().add(meeting3);
        entityManager.getTransaction().commit();
    }

    @Test
    public void deleteTest(){
        EntityManager entityManager = EMUtil.getEntityManager("lesson5.test");
        entityManager.getTransaction().begin();
        Meeting meeting = entityManager.find(Meeting.class,1L);
        entityManager.remove(meeting);
        entityManager.getTransaction().commit();
    }
}
