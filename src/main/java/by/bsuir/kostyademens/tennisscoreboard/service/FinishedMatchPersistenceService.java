package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.dao.MatchDao;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;

import java.util.List;

public class FinishedMatchPersistenceService {

    private final SessionFactoryUtil sessionFactoryUtil = SessionFactoryUtil.getInstance();

    private final MatchDao matchDao = new MatchDao(sessionFactoryUtil);

    public void saveMatch(Match match) {
        matchDao.save(match);
    }

    public List<Match> selectMatches(int page, int recordsPerPage) {
        return matchDao.getMatches(page, recordsPerPage);
    }

    public List<Match> selectMatchesFilteredByName(String name, int page, int recordsPerPage) {
        return matchDao.findByName(name, page, recordsPerPage);
    }

    public long getTotalMatchesCount() {
        return matchDao.countAll();
    }

    public long getTotalMatchesCountByName(String name) {
        return matchDao.countByName(name);
    }
}
