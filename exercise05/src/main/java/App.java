import entity.*;
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

import java.util.Arrays;
import java.util.Date;

public class App {
    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources metadataSources = new MetadataSources(build);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        operation3(sessionFactory);
        sessionFactory.close();
    }

//    private static void operation4(SessionFactory sessionFactory) {
//        operation2(sessionFactory);
//    }
//
    private static void operation3(SessionFactory sessionFactory) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Customer customer = new Customer();
            customer.setFirstname("Nguyen");
            customer.setLastname("Le");
            Order order = new Order();
            order.setCustomer(customer);
            order.setDate(new Date());
            order.setOrderId("OrderId1");

            customer.setOrders(Arrays.asList(order));
            session.persist(customer);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }
//
//    private static void operation2(SessionFactory sessionFactory) {
//        try (Session session = sessionFactory.openSession()) {
//            session.createQuery("from Book").stream().forEach(System.out::println);
//        }
//    }

    private static void a(SessionFactory sessionFactory) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Customer customer = new Customer();
            customer.setFirstname("Minh");
            customer.setLastname("Tran");
            session.save(customer);
            Order order = new Order();
            order.setCustomer(customer);
            order.setDate(new Date());
            order.setOrderId("OrderId1");

            OrderLine orderLine = new OrderLine();
            orderLine.setQuantity(10);
            session.save(orderLine);

            Product product = new Product();
            product.setDescription("product 1 desc");
            product.setName("Product 1");
            session.save(product);

            orderLine.setProduct(product);
            session.save(orderLine);

            CD cd = new CD();
            cd.setArtist("CD Artist");
            cd.setName("CD product");
            cd.setDescription("CD Description");
            session.save(cd);
            OrderLine cdOrderLine = new OrderLine();
            cdOrderLine.setProduct(cd);
            cdOrderLine.setQuantity(12);
            session.save(cdOrderLine);

            DVD dvd = new DVD();
            dvd.setGenre("Action");
            dvd.setName("DVD product");
            dvd.setDescription("DVD Description");
            session.save(dvd);
            OrderLine dvdOrderLine = new OrderLine();
            dvdOrderLine.setProduct(dvd);
            dvdOrderLine.setQuantity(12);
            session.save(dvdOrderLine);

            Book book = new Book();
            book.setTitle("Book title 1");
            book.setName("DVD product");
            book.setDescription("DVD Description");
            session.save(book);
            OrderLine bookOrderLine = new OrderLine();
            bookOrderLine.setProduct(dvd);
            bookOrderLine.setQuantity(12);
            session.save(bookOrderLine);


            order.setOrderLines(Arrays.asList(orderLine, cdOrderLine, dvdOrderLine, bookOrderLine));
            session.save(order);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
        }

        try (Session session = sessionFactory.openSession()) {
            session.createQuery("from Order").stream().forEach(System.out::println);
            session.createQuery("from Customer").stream().forEach(System.out::println);
            session.createQuery("from OrderLine").stream().forEach(System.out::println);
        }
    }

}
