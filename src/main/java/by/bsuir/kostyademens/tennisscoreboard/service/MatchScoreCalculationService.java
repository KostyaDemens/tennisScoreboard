package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatusUtil;
import by.bsuir.kostyademens.tennisscoreboard.util.PlayerStatusUtil;


public class MatchScoreCalculationService {





    /*
    Such method is applicable when the number of points between two player is 40
     */
    public void advantageCountPoint(Player playerOne, Player playerTwo) {
        int firstPlayerPoints = playerOne.getPlayerScore().getPoint().ordinal();
        int secondPlayerPoints = playerTwo.getPlayerScore().getPoint().ordinal();

        if (firstPlayerPoints != 0 && secondPlayerPoints != 0) {
            Player winner = (playerOne.getPlayerStatusUtil() == PlayerStatusUtil.POINT_WINNER) ? playerOne : playerTwo;
            Player loser = (winner == playerOne) ? playerTwo : playerOne;

            winner.getPlayerScore().setPoint(Point.ADVANTAGE);
            loser.getPlayerScore().setPoint(Point.FORTY);
            loser.setPlayerStatusUtil(PlayerStatusUtil.POINT_LOSER);
        } else {
            resetPlayersPoints(playerOne, playerTwo);
        }
    }


    public void calculatePoints(Player player) {
        if (player.getPlayerScore().getPoint() == Point.FORTY) {
            player.getPlayerScore().winPoint();
            player.setPlayerStatusUtil(PlayerStatusUtil.POINT_WINNER);
        } else {
            incrementSets(player);
            /*
            TODO вместо отдельного места возможно стоило сделать как выше (player.getPlayerScore.winSet)
                Или наоборот - отдельный метод incrementPoints как ниже
             */
            player.getPlayerScore().setPoint(Point.LOVE);
        }
    }

    public void incrementSets(Player player) {
        player.getPlayerScore().winSet();
    }


    public int getPointsDifference(Player playerOne, Player playerTwo) {
        return Math.abs(playerOne.getPlayerScore().getPoint().ordinal() - playerTwo.getPlayerScore().getPoint().ordinal());
    }

    public void resetPlayersPoints(Player playerOne, Player playerTwo) {
        playerOne.getPlayerScore().setPoint(Point.LOVE);
        playerTwo.getPlayerScore().setPoint(Point.LOVE);
    }


    private boolean isTieBrake(Match match) {
        return match.getMatchStatus() == MatchStatusUtil.TIE_BRAKE;
    }

    private boolean isMatchFinished(Match match) {
        return match.getMatchStatus() == MatchStatusUtil.FINISHED;
    }
}
