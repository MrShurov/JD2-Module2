package lesson6;

import lesson6.entity.Person.Department;
import lesson6.entity.Person.Person;
import lesson6.entity.Person.PersonInfo;
import lesson6.util.EMUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static javafx.beans.binding.Bindings.lessThan;
import static org.junit.Assert.assertThat;

public class Lesson6Test {

    @BeforeClass
    /*@Test*/
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
        PersonInfo personInfo3 = new PersonInfo(null, "Brest", "Vostochnay", "+37544745678", 2, null);
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
        entityManager.clear();
    }

    @Test
    public void getByNameTest() {
        EntityManager entityManager = EMUtil.getEntityManager();
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
    public void distinctTest() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        List<Person> people = entityManager.createQuery(
                "select distinct a from Person a where personInfo.city = 'Minsk'", Person.class).getResultList();
        people.forEach(System.out::println);
    }

    @Test
    public void countAndGroupByTest() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        javax.persistence.Query query = entityManager.createQuery("select count(p.name), p.name from Person p group by p.name");
        query.getResultList().forEach(people -> {
            Object[] peoples = (Object[]) people;
            System.out.println("Имя: " + peoples[1] + " количество:" + peoples[0]);
        });
    }

    @Test
    public void aggregateTest() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        javax.persistence.Query query = entityManager.createQuery("select avg(p.amountCars), min(p.amountCars), max(p.amountCars), sum(p.amountCars) from PersonInfo p");
        List<Object[]> list = query.getResultList();
        for (Object[] res : list) {
            System.out.println("Среднее кол-во машин: " + res[0] + ", минимальное кол-во машин:" + res[1] + ", максимальное кол-во машин:" + res[2] + ", сумма машин:" + res[3]);
        }
    }

    @Test
    public void insertSelectTest() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        entityManager.getTransaction().begin();
        Session session = entityManager.unwrap(Session.class);
        javax.persistence.Query query = session.createQuery("insert into Person (surname,id) select p.surname, p.id + 20 from Person as p where p.name = 'Egor'");
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Test
    public void deleteTest() {
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        entityManager.getTransaction().begin();
        javax.persistence.Query query = entityManager.createQuery("delete from Person p where p.name=:name");
        query.setParameter("name", "Egor").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Test
    public void PaginationTest(){
        EntityManager entityManager = EMUtil.getEntityManager("lesson6.test");
        entityManager.getTransaction().begin();
        int maxResultsOnPage = 2;
        /*int maxResultsOnPage = 3;
        int maxResultsOnPage = 5;*/
        int amountPage;
        int max;
        int size;
        javax.persistence.Query query = entityManager.createQuery("from Person");
        if ((size = query.getResultList().size()) % maxResultsOnPage == 0){
            max = size / maxResultsOnPage;
        } else max = size / maxResultsOnPage + 1;
        for (int i = 1; i<= max; i++){
            amountPage = i;
            query.setFirstResult((amountPage-1)*maxResultsOnPage)
                    .setMaxResults(maxResultsOnPage);
            query.getResultList().forEach(System.out::println);
            System.out.println("Страница " + amountPage);
        }
        entityManager.getTransaction().commit();
    }
}
