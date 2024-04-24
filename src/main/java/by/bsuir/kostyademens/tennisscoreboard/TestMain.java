package by.bsuir.kostyademens.tennisscoreboard;

import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;


public class TestMain {
    public static void main(String[] args) {
//        Point pointNumberUtil = Point.FIFTEEN;

//        System.out.println(pointNumberUtil.getNumericValue() +
        Player player = new Player();
        player.getPlayerScore().setPoint(Point.THIRTY);
        System.out.println(player.getPlayerScore().getPoint());
    }
}
