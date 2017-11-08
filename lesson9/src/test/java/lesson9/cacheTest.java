package lesson9;

import lesson9.entity.Author;
import lesson9.entity.Book;
import lesson9.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class cacheTest {
    private static final Logger logger = LogManager.getLogger(cacheTest.class);

    @SuppressWarnings("Duplicates")
    @Before
    public void init(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Author author = new Author("Александр","Пушкин");
        Author author1 = new Author("Николай","Гоголь");
        entityManager.persist(author);
        entityManager.persist(author1);
        Book book = new Book(null,"Мёртвые души",1842,author1);
        Book book1 = new Book(null,"Тарас Бульба",1835,author1);
        Book book2 = new Book(null,"Шинель",1842,author1);
        Book book3 = new Book(null,"Капитанская дочка",1836 ,author);
        Book book4 = new Book(null,"Пиковая дама",1834 ,author);
        Book book5 = new Book(null,"Руслан и Людмила",null ,author);
        entityManager.persist(book);
        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.persist(book3);
        entityManager.persist(book4);
        entityManager.persist(book5);
        entityManager.getTransaction().commit();
    }

    @Test
    public void entityCache(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Author author = entityManager.find(Author.class,1L);
        System.out.println(author);
        Author author2 = entityManager.find(Author.class,2L);
        System.out.println(author2);
        Author author4 = entityManager.find(Author.class,1L);
        System.out.println(author4);
        entityManager.clear();
        System.out.println("Clear");
        Author author1 = entityManager.find(Author.class,1L);
        System.out.println(author1);
        Author author3 = entityManager.find(Author.class,1L);
        System.out.println(author3);
        try{
            Thread.sleep(5000);
        } catch (Exception e){
            e.printStackTrace();
        }
        Author author5 = entityManager.find(Author.class,1L);
        System.out.println(author5);
    }

    @Test
    public void queryTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(
                "select a " +
                        "from Author a " +
                        "where a.name = 'Александр'").setCacheable(true);
        System.out.println(query.getSingleResult());
        System.out.println(query.getSingleResult());
        entityManager.clear();
        System.out.println("Clear");
        System.out.println(query.getSingleResult());
        System.out.println(query.getSingleResult());
        try{
            Thread.sleep(5000);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(query.getSingleResult());
    }
}
