package by.bsuir.kostyademens.tennisscoreboard.dao;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;
import org.hibernate.Session;

import java.util.List;

public class MatchDao {

    private final SessionFactoryUtil sessionFactoryUtil;


    public MatchDao(SessionFactoryUtil sessionFactoryUtil) {
        this.sessionFactoryUtil = sessionFactoryUtil;
        sessionFactoryUtil.init();
    }
    public void save(Match match) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    public List<Match> viewAllMatches(int start, int total) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            List<Match> matches = session.createQuery("SELECT m from Match m limit " + (start - 1) + "," + total)
                    .getResultList();
            session.getTransaction().commit();
            return matches;
        }
    }
}
