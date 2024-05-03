package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.dao.MatchDao;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;

import java.util.List;

public class FinishedMatchPersistenceService {

    private final SessionFactoryUtil sessionFactoryUtil = new SessionFactoryUtil();

    private final MatchDao matchDao = new MatchDao(sessionFactoryUtil);

    public void saveMatch(Match match) {
        matchDao.save(match);
    }

    public List<Match> selectAllMatches(int page, int recordsPerPage) {
        return matchDao.viewAllMatches(page, recordsPerPage);
    }

    public int getNoOfRecords() {
        return matchDao.getNoOfRecords();
    }
}
