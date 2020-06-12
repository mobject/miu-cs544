package cs544.exercise16_1.bank.util;

import cs544.exercise16_1.bank.dao.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ExerciseUtils {

    public static Session getHibernateSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.getCurrentSession();
    }
}
