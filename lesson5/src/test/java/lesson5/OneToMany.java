package lesson5;

import lesson5.entity.OneToMany.Department;
import lesson5.entity.OneToMany.Person;
import lesson5.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class OneToMany {
    @Test
    public void CeateTest(){
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
}
