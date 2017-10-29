package lesson5;

import lesson5.entity.OneToMany.Department;
import lesson5.entity.OneToMany.Person;
import lesson5.util.EMUtil;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class OneToMany {

    private static final Logger logger = LogManager.getLogger(OneToMany.class);

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
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Person personFromDB = entityManager.find(Person.class,1L);
        personFromDB.setName("Gena");
        entityManager.getTransaction().commit();
    }

    @Test
    public void GetListTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Department department = new Department("Sykpyn");
        Department department1 = new Department("Lex");
        Person person = new Person(null, "Herny","Dum",department);
        Person person1 = new Person(null, "Herny","Dum",department);
        Person person2 = new Person(null, "Herny","Dum",department1);
        Person person3 = new Person(null, "Herny","Dum",department1);
        List<Person> people1 = new ArrayList<>();
        List<Person> people = new ArrayList<>();
        people1.add(person);
        people1.add(person1);
        people.add(person2);
        people.add(person3);
        department.setPeople(people1);
        department1.setPeople(people);
        entityManager.persist(person);
        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.persist(person3);
        entityManager.getTransaction().commit();
        List<Department> departments = entityManager.createQuery(
                "select d from Department d " +
                        "inner join d.people p " +
                        "where p.name like '%'", Department.class)
                .getResultList();
        for (Department departmentTest : departments) {
            logger.error("Department {} contains {} people",
                    departmentTest.getId(),
                    departmentTest.getPeople().size());
        }
        entityManager.close();
    }
}
