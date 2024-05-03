package by.bsuir.kostyademens.tennisscoreboard.dao;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import org.hibernate.Session;

import java.util.List;

public class MatchDao {

    private final SessionFactoryUtil sessionFactoryUtil;

    @Getter
    private int noOfRecords;

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

    public List<Match> viewAllMatches(int offset, int noOfRecords) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            TypedQuery<Match> matches = session.createQuery("SELECT m from Match m", Match.class);
            matches.setFirstResult(offset);
            matches.setMaxResults(noOfRecords);

            //Получить общее кол-во записей в БД
            TypedQuery<Long> countQuery = session.createQuery("SELECT COUNT(m) FROM Match m", Long.class);
            long totalRecords = countQuery.getSingleResult();
            this.noOfRecords = (int) totalRecords;

            session.getTransaction().commit();
            return matches.getResultList();
        }
    }

}
