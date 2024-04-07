package by.bsuir.kostyademens.tennisscoreboard;

import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestMain {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Player.class);

        SessionFactory factory = configuration.buildSessionFactory();

        try (factory) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Player player = session.get(Player.class, 8);
            System.out.println(player);
            session.getTransaction().commit();

        }
    }
}
