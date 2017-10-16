package lesson4;

import lesson4.entity.TablePerSubclass.Student;
import lesson4.entity.TablePerSubclass.Teacher;
import lesson4.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class TablePerSubclassTest {
    @Test
    public void CreateTeacherTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Teacher teacher = new Teacher("Math");
        Student student = new Student("10");
        entityManager.persist(teacher);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    @Test
    public void DeleteUniversityTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Student student = new Student("10");
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.getTransaction().begin();
        Student fromDB = entityManager.find(Student.class,1);
        entityManager.remove(fromDB);
        entityManager.getTransaction().commit();
    }

    @Test
    public void UpdateTeacherTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Teacher teacher = new Teacher("Demo");
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.getTransaction().begin();
        Teacher teacherFromDB = entityManager.find(Teacher.class,1);
        teacherFromDB.setSubject("English");
        entityManager.getTransaction().commit();
    }
}
