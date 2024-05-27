package by.bsuir.kostyademens.tennisscoreboard.util;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

  @Getter private static SessionFactoryUtil instance = new SessionFactoryUtil();
  private final SessionFactory sessionFactory;

  private SessionFactoryUtil() {
    sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
  }

  public Session getSession() {
    return sessionFactory.openSession();
  }
}
