package lesson4;

import lesson4.entity.Person;
import lesson4.entity.Student;
import lesson4.entity.Worker;
import lesson4.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class PersonTest {
    @Test
    public void SavePerson(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Person person = new Person(null,"32","3e");
        Worker worker = new Worker("dfs");
        Student student = new Student("ds");
        entityManager.persist(person);
        entityManager.persist(worker);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    @Test
    public void DeletePerson(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Student student = new Student("ds");
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Student studentFromDB = entityManager.find(Student.class,1);
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    @Test
    public void UpdatePerson(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Worker worker = new Worker("ds");
        entityManager.persist(worker);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Worker workerFromDB = entityManager.find(Worker.class,1);
        worker.setCompany("it");
        entityManager.getTransaction().commit();
    }
}
