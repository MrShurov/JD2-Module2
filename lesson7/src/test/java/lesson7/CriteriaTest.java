package lesson7;

import lesson7.entity.Author;
import lesson7.entity.Book;
import org.junit.Before;
import org.junit.Test;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaTest {

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
    public void likeTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);
        bookCriteriaQuery.select(bookRoot).where(criteriaBuilder.like(bookRoot.get("name"), "%д%"));
        List<Book> books = entityManager.createQuery(bookCriteriaQuery).getResultList();
        books.forEach(System.out::println);
    }

    @Test
    public void betweenTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);
        bookCriteriaQuery.select(bookRoot).where(criteriaBuilder.between(bookRoot.get("year"), 1830,1840));
        List<Book> books = entityManager.createQuery(bookCriteriaQuery).getResultList();
        books.forEach(System.out::println);
    }

    @Test
    public void isNotNullTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);
        bookCriteriaQuery.select(bookRoot).where(criteriaBuilder.isNotNull(bookRoot.get("year")));
        List<Book> books = entityManager.createQuery(bookCriteriaQuery).getResultList();
        books.forEach(System.out::println);
    }

    @Test
    public void pagingTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        int pageNumber = 1;
        int pageSize = 3;
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);
        bookCriteriaQuery.select(bookRoot);
        TypedQuery<Book> typedQuery = entityManager.createQuery(bookCriteriaQuery);
        typedQuery.setFirstResult(pageSize * (pageNumber-1));
        typedQuery.setMaxResults(pageSize);
        List<Book> books = typedQuery.getResultList();
        books.forEach(System.out::println);
    }

    @Test
    public void descTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);
        bookCriteriaQuery.select(bookRoot).orderBy(criteriaBuilder.desc(bookRoot.get("year")));
        List<Book> books = entityManager.createQuery(bookCriteriaQuery).getResultList();
        books.forEach(System.out::println);
    }

    @Test
    public void ascTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);
        bookCriteriaQuery.select(bookRoot).orderBy(criteriaBuilder.asc(bookRoot.get("year")));
        List<Book> books = entityManager.createQuery(bookCriteriaQuery).getResultList();
        books.forEach(System.out::println);
    }

    @Test
    public void maxTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaBuilderQuery = criteriaBuilder.createQuery(Integer.class);
        criteriaBuilderQuery.select(criteriaBuilder.max(criteriaBuilderQuery.from(Book.class).get("year")));
        Integer book = entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
        System.out.println(book);
    }

    @Test
    public void minTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaBuilderQuery = criteriaBuilder.createQuery(Integer.class);
        criteriaBuilderQuery.select(criteriaBuilder.min(criteriaBuilderQuery.from(Book.class).get("year")));
        Integer book = entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
        System.out.println(book);
    }

    @Test
    public void avgTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> criteriaBuilderQuery = criteriaBuilder.createQuery(Double.class);
        criteriaBuilderQuery.select(criteriaBuilder.avg(criteriaBuilderQuery.from(Book.class).get("year")));
        Double book = entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
        System.out.println(book);
    }

    @Test
    public void sumTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> criteriaBuilderQuery = criteriaBuilder.createQuery(Integer.class);
        criteriaBuilderQuery.select(criteriaBuilder.sum(criteriaBuilderQuery.from(Book.class).get("year")));
        Integer book = entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
        System.out.println(book);
    }

    @Test
    public void countTest(){
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaBuilderQuery = criteriaBuilder.createQuery(Long.class);
        criteriaBuilderQuery.select(criteriaBuilder.count(criteriaBuilderQuery.from(Book.class)));
        Long book = entityManager.createQuery(criteriaBuilderQuery).getSingleResult();
        System.out.println(book);
    }

    @Test
    public void logicalPredicateTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> bookCriteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.like(bookRoot.get("name"), "%д%"),
                criteriaBuilder.le(bookRoot.get("year"), 1835)
        );
        bookCriteriaQuery.select(bookRoot).where(predicate);

        List<Book> books = entityManager.createQuery(bookCriteriaQuery).getResultList();
        books.forEach(System.out::println);
    }

    @Test
    public void orderByTest() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> bookCriteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Book> bookRoot = bookCriteriaQuery.from(Book.class);
        bookCriteriaQuery.groupBy(bookRoot.get("name"));
        bookCriteriaQuery.multiselect(bookRoot.get("name"), criteriaBuilder.count(bookRoot));
        bookCriteriaQuery.where(criteriaBuilder.equal(bookRoot.get("name"), "Капитанская дочка"));

        List<Tuple> tuples = entityManager.createQuery( bookCriteriaQuery ).getResultList();
        tuples.forEach(t->{
            String name = (String) t.get( 0 );
            long count = (long) t.get( 1 );
            System.out.println("Name: " + name + " count: " + count);
        });
    }
}
