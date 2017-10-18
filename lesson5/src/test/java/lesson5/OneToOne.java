package lesson5;

import lesson5.entity.OneToOne.Person;
import lesson5.entity.OneToOne.PersonInfo;
import lesson5.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class OneToOne {
    @Test
    public void SaveTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Person person = new Person(null,"Egor","Shurov",null);
        PersonInfo personInfo = new PersonInfo(null,"Minsk","Vostochnay","+37544745678",null);
        person.setPersonInfo(personInfo);
        personInfo.setPerson(person);
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }

    @Test
    public void DeleteTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Person person = new Person(null,"Egor","Shurov",null);
        PersonInfo personInfo = new PersonInfo(null,"Minsk","Vostochnay","+37544745678",null);
        person.setPersonInfo(personInfo);
        personInfo.setPerson(person);
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        entityManager.remove(person);
        entityManager.getTransaction().commit();
    }

    @Test
    public void UpdateTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Person person = new Person(null,"Egor","Shurov",null);
        PersonInfo personInfo = new PersonInfo(null,"Minsk","Vostochnay","+37544745678",null);
        person.setPersonInfo(personInfo);
        personInfo.setPerson(person);
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Person personFromDB = entityManager.find(Person.class,1L);
        PersonInfo personInfo1 = new PersonInfo(null,"Milan","Central","+37544745678",null);
        personFromDB.setPersonInfo(personInfo1);
        entityManager.persist(personFromDB);
        entityManager.getTransaction().commit();
    }
}
