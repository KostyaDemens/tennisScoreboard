package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.dao.PlayerDao;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;

public class PlayerService extends PlayerDao {

    SessionFactoryUtil sessionFactoryUtil = new SessionFactoryUtil();

    private final PlayerDao playerDao = new PlayerDao(sessionFactoryUtil);

    public PlayerService(SessionFactoryUtil sessionFactoryUtil) {
        super(sessionFactoryUtil);
    }

    public void createNewPlayer() {

    }
}
