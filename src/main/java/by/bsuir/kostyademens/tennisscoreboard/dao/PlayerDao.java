package by.bsuir.kostyademens.tennisscoreboard.dao;

import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;
import jakarta.persistence.NoResultException;
import java.util.Optional;
import org.hibernate.Session;

public class PlayerDao {
  private final SessionFactoryUtil sessionFactoryUtil;

  public PlayerDao() {
    this.sessionFactoryUtil = SessionFactoryUtil.getInstance();
  }

  public Optional<Player> findByName(String name) {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      Player player =
          session
              .createQuery("FROM Player p WHERE p.name = :name", Player.class)
              .setParameter("name", name)
              .getSingleResult();
      session.getTransaction().commit();
      return Optional.of(player);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  public Player save(Player player) {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      session.persist(player);
      session.getTransaction().commit();
      return player;
    }
  }
}
