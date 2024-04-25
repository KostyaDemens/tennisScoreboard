package by.bsuir.kostyademens.tennisscoreboard;

import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;


public class TestMain {
    public static void main(String[] args) {
        Player player = new Player();
        player.getPlayerScore().setPoint(Point.ADVANTAGE);
        System.out.println(player.getPlayerScore());
//        System.out.println(player.getPlayerScore().getPoint().ordinal());
    }
}
