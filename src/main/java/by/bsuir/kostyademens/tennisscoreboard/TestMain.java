package by.bsuir.kostyademens.tennisscoreboard;

import by.bsuir.kostyademens.tennisscoreboard.dao.MatchDao;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;

import java.util.List;


public class TestMain {
    public static void main(String[] args) {

        SessionFactoryUtil sessionFactoryUtil = new SessionFactoryUtil();
        MatchDao dao = new MatchDao(sessionFactoryUtil);

        List<Match> matches = dao.findByName("BOB", 1, 5);
//        List<Match> matches = dao.viewAllMatches(1, 5);
        System.out.println(matches);
        System.out.println(matches.size());
    }
}
