package lesson8;

import lesson8.entity.Book;
import lesson8.entity.BookAll;
import lesson8.entity.BookDirty;
import lesson8.entity.BookVersion;
import lesson8.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.RollbackException;

public class emTest {

    @Before
    public void init() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        BookVersion bookVersion = new BookVersion(null, "Мёртвые души", "Николай Гоголь", null);
        BookAll bookAll = new BookAll(null, "Мёртвые души", "Николай Гоголь");
        BookDirty bookDirty = new BookDirty(null, "Мёртвые души", "Николай Гоголь");
        Book book = new Book(null, "Мёртвые души", "Николай Гоголь");
        entityManager.persist(bookVersion);
        entityManager.persist(bookAll);
        entityManager.persist(bookDirty);
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void versionTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        BookVersion bookVersion = entityManager.find(BookVersion.class, 1L);
        bookVersion.setName("Тарас Бульба");
        entityManager.getTransaction().commit();
        entityManager.close();
        Integer expectedVersion = 1;
        Assert.assertEquals("It works", expectedVersion, bookVersion.getVersion());
    }

    @Test
    public void versionWithExceptionTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        BookVersion bookVersion = entityManager.find(BookVersion.class, 1L);
        try {
            Thread.sleep(1000);
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                BookVersion bookVersion1 = entityManager1.find(BookVersion.class, 1L);
                bookVersion1.setName("2 поток");
                entityManager1.getTransaction().commit();
                System.out.println("2 поток");
                entityManager1.close();
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookVersion.setName("Тарас Бульба");
        entityManager.getTransaction().commit();
        System.out.println("1 поток");
        entityManager.close();
    }

    @Test
    public void allWithExceptionTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        BookAll bookAll = entityManager.find(BookAll.class, 1L);
        try {
            Thread.sleep(1000);
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                BookAll bookAll1 = entityManager1.find(BookAll.class, 1L);
                bookAll1.setName("2 поток");
                entityManager1.getTransaction().commit();
                System.out.println("2 поток");
                entityManager1.close();
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookAll.setName("Тарас Бульба");
        entityManager.getTransaction().commit();
        System.out.println("1 поток");
        entityManager.close();
    }

    @Test
    public void dirtyWithExceptionTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        BookDirty bookDirty = entityManager.find(BookDirty.class, 1L);
        try {
            Thread.sleep(1000);
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                BookDirty bookDirty1 = entityManager1.find(BookDirty.class, 1L);
                bookDirty1.setName("2 поток");
                entityManager1.getTransaction().commit();
                System.out.println("2 поток");
                entityManager1.close();
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookDirty.setName("Тарас Бульба");
        entityManager.getTransaction().commit();
        System.out.println("1 поток");
        entityManager.close();
    }

    @Test
    public void forceTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        BookVersion bookVersion = entityManager.find(BookVersion.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        bookVersion.setName("Тарас Бульба");
        entityManager.getTransaction().commit();
        entityManager.close();
        Integer expectedVersion = 2;
        Assert.assertEquals("It works", expectedVersion, bookVersion.getVersion());
    }

    @Test
    public void forceWithExceptionTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        BookVersion bookVersion = entityManager.find(BookVersion.class, 1L, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        try {
            Thread.sleep(1000);
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                BookVersion bookVersion1 = entityManager1.find(BookVersion.class, 1L);
                bookVersion1.setName("updated Book");
                entityManager1.getTransaction().commit();
                System.out.println("2 поток");
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookVersion.setName("Тарас Бульба");
        entityManager.getTransaction().commit();
        System.out.println("1 поток");
        entityManager.close();
    }

    @Test
    public void pessimisticReadTest(){
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            entityManager.getTransaction().begin();
            Book book = entityManager.find(Book.class, 1L, LockModeType.PESSIMISTIC_READ);
            System.out.println(book);
            book.setName("Тарас Бульба");
            Thread.sleep(500);
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                Book book1 = entityManager1.find(Book.class, 1L);
                entityManager1.getTransaction().commit();
                System.out.println(book1 + "only read");
            }).start();
            Thread.sleep(500);
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                Book book2 = entityManager1.find(Book.class, 1L);
                book2.setAuthor("Egor");
                entityManager1.getTransaction().commit();
                System.out.println(book2 + "updated");
            }).start();
            entityManager.getTransaction().commit();
            System.out.println(entityManager.find(Book.class,1L) + "last updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pessimisticWriteTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, 1L, LockModeType.PESSIMISTIC_WRITE);
        System.out.println(book);
        book.setName("J");
        entityManager.flush();
        System.out.println(entityManager.find(Book.class, 1L));
        try {
            Thread.sleep(1000);
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                Book book2 = entityManager1.find(Book.class, 1L);
                entityManager1.getTransaction().commit();
                System.out.println(book2 + "read");
                entityManager1.close();
            }).start();
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                Session session = entityManager1.unwrap(Session.class);
                entityManager1.getTransaction().begin();
                Query query = session.createQuery("from Book where id=1");
                entityManager1.getTransaction().commit();
                System.out.println(query.getSingleResult() + " read query");
                entityManager1.close();
            }).start();
            Thread.sleep(500);
            new Thread(() -> {
                EntityManager entityManager1 = HibernateUtil.getEntityManager();
                entityManager1.getTransaction().begin();
                Book book2 = entityManager1.find(Book.class, 1L);
                book2.setAuthor("Igor");
                entityManager1.getTransaction().commit();
                System.out.println(book2 + "updated");
                entityManager1.close();
            }).start();
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityManager.getTransaction().commit();
        System.out.println(book + "after main commit");
        entityManager.close();
    }
}
