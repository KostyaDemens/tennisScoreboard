package by.bsuir.kostyademens.tennisscoreboard;

import by.bsuir.kostyademens.tennisscoreboard.dao.PlayerDao;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;


public class TestMain {
    public static void main(String[] args) {
        SessionFactoryUtil sessionFactoryUtil = new SessionFactoryUtil();
        sessionFactoryUtil.init();

        try {
            PlayerDao playerDao = new PlayerDao(sessionFactoryUtil);
            playerDao.findByName("IGOR");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtil.shotDown();
        }
    }
}
