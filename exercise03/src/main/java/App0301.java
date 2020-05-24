import entity.Car;
import entity.Owner;
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

public class App0301 {
    private static Logger LOGGER = LoggerFactory.getLogger(App0301.class);

    public static void main(String[] args) {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources metadataSources = new MetadataSources(build);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Car car = new Car();
            car.setBrand("BMW");
            car.setPrice(100000);
            car.setYear(2020);
            Owner owner = new Owner();
            owner.setName("Minh Tran");
            owner.setAddress("123 street, City, State, Zip");
            car.setOwner(owner);
            session.persist(car);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }

        try(Session session = sessionFactory.openSession()){
            session.createQuery("from Car", Car.class).stream().forEach(System.out::println);
        }
        sessionFactory.close();
    }

}
