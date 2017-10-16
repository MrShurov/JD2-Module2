package lesson4;

import lesson4.entity.Teacher;
import lesson4.entity.University;
import lesson4.util.EMUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class DataEntityTest {
    @Test
    public void CreateTeacherTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Teacher teacher = new Teacher("Gena","Ivanov");
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
    }

    @Test
    public void DeleteUniversityTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        University university = new University("SBMT");
        entityManager.persist(university);
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.getTransaction().begin();
        University fromDB = entityManager.find(University.class,1L);
        entityManager.remove(fromDB);
        entityManager.getTransaction().commit();
    }

    @Test
    public void UpdateTeacherTest(){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Teacher teacher = new Teacher("Gena","Ivanov");
        entityManager.persist(teacher);
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.getTransaction().begin();
        Teacher teacherFromDB = entityManager.find(Teacher.class,1L);
        teacherFromDB.setName("Egor");
        entityManager.getTransaction().commit();
    }
}
