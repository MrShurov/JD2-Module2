package lesson5;

import lesson5.entity.OneToMany.Department;
import lesson5.entity.OneToMany.Person;
import lesson5.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class OneToMany {
    @Test
    public void CreateTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Department department = new Department("Sykpyn");
        Department department1 = new Department("Lex");
        Person person = new Person(null, "Herny","Dum",department);
        Person person1 = new Person(null, "Herny","Dum",department);
        Person person2 = new Person(null, "Herny","Dum",department1);
        Person person3 = new Person(null, "Herny","Dum",department1);
        entityManager.persist(department);
        entityManager.persist(department1);
        entityManager.persist(person);
        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.persist(person3);
        entityManager.getTransaction().commit();
    }
    @Test
    public void DeleteTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Department department = new Department("Sykpyn");
        Person person = new Person(null, "Herny","Dum",department);
        entityManager.persist(department);
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
        Department department = new Department("Sykpyn");
        Person person = new Person(null, "Herny","Dum",department);
        ArrayList<Person> people = new ArrayList<>();
        people.add(person);
        department.setPeople(people);
        entityManager.persist(department);
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Person personFromDB = entityManager.find(Person.class,2L);
        System.out.println(personFromDB.getDepartment());
        personFromDB.setName("Gena");
        entityManager.getTransaction().commit();
    }
}
