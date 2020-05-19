import entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class BookApp {
    private static Logger LOGGER = LoggerFactory.getLogger(BookApp.class);

    public static void main(String[] args) {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources metadataSources = new MetadataSources(build);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        LOGGER.info("-----------------operation 1-----------------");
        operation1(sessionFactory);
        LOGGER.info("-----------------operation 2-----------------");
        operation2(sessionFactory);
        LOGGER.info("-----------------operation 3-----------------");
        operation3(sessionFactory);
        LOGGER.info("-----------------operation 4-----------------");
        operation4(sessionFactory);
        sessionFactory.close();
    }

    private static void operation4(SessionFactory sessionFactory) {
        operation2(sessionFactory);
    }

    private static void operation3(SessionFactory sessionFactory) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            List<Book> books = session.createQuery("from Book").list();
            Book book = books.get(0);
            book.setTitle("UPDATED Title");
            book.setPrice(100);
            session.remove(books.get(1));
            session.remove(books.get(2));
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    private static void operation2(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.createQuery("from Book").stream().forEach(System.out::println);
        }
    }

    private static void operation1(SessionFactory sessionFactory) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Book book1 = new Book("Book1", "ISBN 1", "Author 1", 15, new Date());
            Book book2 = new Book("Book2", "ISBN 2", "Author 2", 10, new Date());
            Book book3 = new Book("Book3", "ISBN 3", "Author 3", 90, new Date());
            session.persist(book1);
            session.persist(book2);
            session.persist(book3);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }
}
