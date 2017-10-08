package hlesson2.services;

import hlesson2.entity.User;
import hlesson2.util.EMUtil;

import javax.persistence.EntityManager;

public  class UserDao {
    public static void CreateUser(Integer id,String name,String password){
        EntityManager entityManager = EMUtil.getEntityManager();
        entityManager.getTransaction().begin();
        User user = new User(id,name,password);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
}
