package by.bsuir.kostyademens.tennisscoreboard.util;


import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

    @Getter
    private final static SessionFactoryUtil instance = new SessionFactoryUtil();

    private SessionFactoryUtil(){}

    private SessionFactory sessionFactory;

    public void init() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
