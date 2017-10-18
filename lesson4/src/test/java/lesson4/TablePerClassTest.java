package lesson4;

import lesson4.entity.TablePerClass.Student;
import lesson4.entity.TablePerClass.Teacher;
import lesson4.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class TablePerClassTest {
    @Test
    public void CreateTeacherTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Teacher teacher = new Teacher("Андрей","Тавкинь","Russian");
        Student student = new Student("Сергей","Потапов","10");
        entityManager.persist(teacher);
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    @Test
    public void DeleteUniversityTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Student student = new Student("Сергей","Потапов","20");
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
        Teacher teacher = new Teacher("Андрей","Тавкинь","Russian");
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.getTransaction().begin();
        Teacher teacherFromDB = entityManager.find(Teacher.class,1);
        teacherFromDB.setSubject("English");
        entityManager.getTransaction().commit();
    }
}
