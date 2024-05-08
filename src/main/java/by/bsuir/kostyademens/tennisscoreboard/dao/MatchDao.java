package by.bsuir.kostyademens.tennisscoreboard.dao;

import by.bsuir.kostyademens.tennisscoreboard.dto.MatchDto;
import by.bsuir.kostyademens.tennisscoreboard.mapper.EntityMapper;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import org.hibernate.Session;

import java.util.ArrayList;
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

    public List<MatchDto> viewAllMatches(int offset, int noOfRecords) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            TypedQuery<Match> matchesQuery = session.createQuery("SELECT m from Match m", Match.class);
            matchesQuery.setFirstResult(offset);
            matchesQuery.setMaxResults(noOfRecords);
            this.noOfRecords = countAllMatchesInTheDataBase();
            return getMatchDtos(session, matchesQuery);
        }
    }

    public List<MatchDto> findByName(String name, int offset, int noOfRecords) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            TypedQuery<Match> matchesQuery = session.createQuery("select m from Match m where m.player1.name = :name OR m.player2.name = :name", Match.class)
                    .setParameter("name", name);
            matchesQuery.setFirstResult(offset);
            matchesQuery.setMaxResults(noOfRecords);
            this.noOfRecords = countMatchesByName(name);
            return getMatchDtos(session, matchesQuery);
        }
    }

    private List<MatchDto> getMatchDtos(Session session, TypedQuery<Match> matchesQuery) {
        List<Match> matches = matchesQuery.getResultList();
        session.getTransaction().commit();
        List<MatchDto> matchDtos = new ArrayList<>();
        for (Match match : matches) {
            matchDtos.add(EntityMapper.toDto(match));
        }
        return matchDtos;
    }

    public int countAllMatchesInTheDataBase() {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            TypedQuery<Long> countQuery = session.createQuery("SELECT COUNT(m) FROM Match m", Long.class);
            long totalRecords = countQuery.getSingleResult();
            this.noOfRecords = (int) totalRecords;
            session.getTransaction().commit();
            return this.noOfRecords;
        }
    }

    public int countMatchesByName(String name) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            TypedQuery<Long> matches = session.createQuery("select count(m) from Match m WHERE m.player1.name = :name or m.player2.name = :name", Long.class)
                    .setParameter("name", name);
            long totalRecords = matches.getSingleResult();
            this.noOfRecords = (int) totalRecords;
            session.getTransaction().commit();
            return this.noOfRecords;
        }
    }

}
