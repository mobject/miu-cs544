import entity.a.Department;
import entity.a.Employee;
import entity.b.Book;
import entity.b.Publisher;
import entity.c.Course;
import entity.c.Student;
import entity.d.Customer;
import entity.d.Reservation;
import entity.f.Office;
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

public class App0302 {
    private static Logger LOGGER = LoggerFactory.getLogger(App0302.class);

    public static void main(String[] args) {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure().build();
        MetadataSources metadataSources = new MetadataSources(build);
        Metadata metadata = metadataSources.getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
//        a(sessionFactory);
//        b(sessionFactory);
//        c(sessionFactory);
//        d(sessionFactory);
        f(sessionFactory);
        sessionFactory.close();
    }

    private static void a(SessionFactory sessionFactory){
        LOGGER.info("----------------a)------------------");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Department department = new Department();
            department.setName("IT Dept");
            Employee employee = new Employee();
            employee.setEmployeeNumber("Emp1");
            employee.setName("Employee Name 1");
            employee.setDepartment(department);
            department.setEmployees(Arrays.asList(employee));
            session.persist(department);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }

        try(Session session = sessionFactory.openSession()){
            session.createQuery("from Department", Department.class).stream().forEach(System.out::println);
        }
    }

    private static void b(SessionFactory sessionFactory){
        LOGGER.info("----------------b)------------------");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Publisher publisher = new Publisher();
            publisher.setName("publisher 1");
            Book book1 = new Book();
            book1.setTitle("book 1");
            book1.setAuthor("Author 1");
            session.save(publisher);
            publisher.addBook(book1);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }

        try(Session session = sessionFactory.openSession()){
            session.createQuery("from Publisher", Publisher.class).stream().forEach(System.out::println);
        }
    }

    private static void c(SessionFactory sessionFactory){
        LOGGER.info("----------------c)------------------");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Student student = new Student();
            student.setFirstName("Minh");
            student.setLastName("Tran");
            Course course = new Course();
            course.setName("Enterprise Architecture");
            course.setCourseNumber("CS544");
            course.addStudent(student);
            session.save(course);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }

        try(Session session = sessionFactory.openSession()){
            LOGGER.info("------PRINT");
            session.createQuery("from Course", Course.class).stream().forEach(System.out::println);
            session.createQuery("from Student", Student.class).stream().forEach(System.out::println);
        }
    }

    private static void d(SessionFactory sessionFactory){
        LOGGER.info("----------------d)------------------");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Customer customer = new Customer();
            customer.setName("Minh Tran");
            Reservation reservation = new Reservation();
            reservation.setDate(new Date());
            customer.setReservations(Arrays.asList(reservation));
            session.save(customer);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }

        try(Session session = sessionFactory.openSession()){
            LOGGER.info("------PRINT");
            session.createQuery("from Customer", Customer.class).stream().forEach(System.out::println);
            session.createQuery("from Reservation", Reservation.class).stream().forEach(System.out::println);
        }
    }


    private static void f(SessionFactory sessionFactory){
        LOGGER.info("----------------e)------------------");
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            entity.f.Department department = new entity.f.Department();
            department.setName("Dept F");
            session.save(department);
            entity.f.Employee employee = new entity.f.Employee();
            employee.setName("Minh Tran");
            employee.setDepartment(department);
            Office office = new Office();
            office.setBuilding("Office 1");
            employee.setOffice(office);
            session.save(employee);
//            department.setEmployees(Arrays.asList(employee));
//            session.save(department);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            if (transaction != null && transaction.getStatus() == TransactionStatus.ACTIVE) {
                transaction.rollback();
            }

        }

        try(Session session = sessionFactory.openSession()){
            LOGGER.info("------PRINT");
            session.createQuery("from Office", Office.class).stream().forEach(System.out::println);
            session.createQuery("from Employee", entity.f.Employee.class).stream().forEach(System.out::println);
            session.createQuery("from Department", entity.f.Department
                    .class).stream().forEach(System.out::println);
        }
    }

}
