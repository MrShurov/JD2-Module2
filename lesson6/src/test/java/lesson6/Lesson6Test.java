package lesson6;

import lesson6.entity.Person.Department;
import lesson6.entity.Person.Person;
import lesson6.entity.Person.PersonInfo;
import lesson6.util.EMUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class Lesson6Test {

    /*@Before*/
    @Test
    public void init() {
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Department department = new Department(null, "Sykpyn");
        Department department1 = new Department(null, "Lex");
        Person person = new Person(null, "Herny", "Dum", null);
        Person person1 = new Person(null, "Egor", "God", null);
        Person person2 = new Person(null, "Fox", "River", null);
        Person person3 = new Person(null, "Egor", "Worm", null);
        PersonInfo personInfo = new PersonInfo(null, "Minsk", "Vostochnay", "+37544745678", 3, null);
        PersonInfo personInfo2 = new PersonInfo(null, "Grodno", "Vostochnay", "+37544745678", 1, null);
        PersonInfo personInfo3 = new PersonInfo(null, "Brest", "Vostochnay", "+37544745678", 0, null);
        PersonInfo personInfo4 = new PersonInfo(null, "Minsk", "Vostochnay", "+37544745678", 2, null);
        person.setPersonInfo(personInfo);
        person1.setPersonInfo(personInfo2);
        person2.setPersonInfo(personInfo3);
        person3.setPersonInfo(personInfo4);
        entityManager.persist(person);
        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.persist(person3);
        entityManager.persist(department);
        entityManager.persist(department1);
        entityManager.getTransaction().commit();
    }

    @Test
    public void getByNameTest() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Person where name='Egor'");
        System.out.println(query.list());
    }

    @Test
    public void getById() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Department where id=9");
        System.out.println(query.getSingleResult());
    }

    @Test
    public void getByTwoFields() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        Session session = entityManager.unwrap(Session.class);
        javax.persistence.Query query = session.createQuery("from Person p where p.name= :name and p.personInfo.id > 4");
        query.setParameter("name", "Egor");
        System.out.println(query.getResultList());
    }

    @Test
    public void groupBy() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from PersonInfo group by amountCars ");
        System.out.println(query.getResultList());
    }

    @Test
    public void inner(){
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("SELECT i.name, i.surname FROM PersonInfo p INNER JOIN Person i ON p.person = i.personInfo");
        System.out.println(query.getResultList());
    }
}
