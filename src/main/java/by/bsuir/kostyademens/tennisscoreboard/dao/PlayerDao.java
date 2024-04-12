package by.bsuir.kostyademens.tennisscoreboard.dao;

import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

public class PlayerDao {
    private SessionFactoryUtil sessionFactoryUtil = new SessionFactoryUtil();

    public PlayerDao(SessionFactoryUtil sessionFactoryUtil) {
        this.sessionFactoryUtil = sessionFactoryUtil;
    }

    public Player findByName(String name) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Player player = session.createQuery("FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            System.out.println(player.getId());
            return player;
        } catch (NoResultException e) {
            throw new RuntimeException("Нету такого имени");
        }
    }

    public void save(Player player) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        }
    }


}
