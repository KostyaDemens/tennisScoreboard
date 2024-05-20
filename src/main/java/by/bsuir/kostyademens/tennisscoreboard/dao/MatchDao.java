package by.bsuir.kostyademens.tennisscoreboard.dao;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.hibernate.Session;

public class MatchDao {

  private final SessionFactoryUtil sessionFactoryUtil;

  public MatchDao() {
    this.sessionFactoryUtil = SessionFactoryUtil.getInstance();
  }

  public void save(Match match) {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      session.persist(match);
      session.getTransaction().commit();
    }
  }

  public List<Match> findPage(int offset, int noOfRecords) {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      TypedQuery<Match> matchesQuery = session.createQuery("SELECT m from Match m", Match.class);
      matchesQuery.setFirstResult(offset);
      matchesQuery.setMaxResults(noOfRecords);
      List<Match> matches = matchesQuery.getResultList();
      session.getTransaction().commit();
      return matches;
    }
  }

  public List<Match> findByPlayerName(String name, int offset, int noOfRecords) {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      TypedQuery<Match> matchesQuery =
          session
              .createQuery(
                  "select m from Match m where m.player1.name = :name OR m.player2.name = :name",
                  Match.class)
              .setParameter("name", name);
      matchesQuery.setFirstResult(offset);
      matchesQuery.setMaxResults(noOfRecords);
      List<Match> matches = matchesQuery.getResultList();
      session.getTransaction().commit();
      return matches;
    }
  }

  public long countAll() {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      TypedQuery<Long> countQuery = session.createQuery("SELECT COUNT(m) FROM Match m", Long.class);
      long totalRecords = countQuery.getSingleResult();
      session.getTransaction().commit();
      return totalRecords;
    }
  }

  public long countByName(String name) {
    try (Session session = sessionFactoryUtil.getSession()) {
      session.beginTransaction();
      TypedQuery<Long> matches =
          session
              .createQuery(
                  "select count(m) from Match m WHERE m.player1.name = :name or m.player2.name = :name",
                  Long.class)
              .setParameter("name", name);
      long totalRecords = matches.getSingleResult();
      session.getTransaction().commit();
      return totalRecords;
    }
  }
}
