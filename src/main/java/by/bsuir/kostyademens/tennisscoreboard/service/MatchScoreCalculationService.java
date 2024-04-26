package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatus;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatusUtil;
import by.bsuir.kostyademens.tennisscoreboard.util.PlayerStatusUtil;


public class MatchScoreCalculationService {

    /*
    Such method is used to make all calculations in the match
     */

    public void countPoints(Match match, Player player) {
        if (MatchStatusUtil.isMatchFinished(match)) {
            return;
        }

        if (MatchStatusUtil.isTieBreak(match)) {
            computeTieBreakPoints(match, player);
            return;
        }

        incrementPoints(player);
        if (isGameFinished(match, player)) {
            resetPlayersPoints(match);
            incrementGames(player);
        }


    }


    /*
    Such method is applicable when the number of games between to players is 6
     */
    public void computeTieBreakPoints(Match match, Player player) {
        incrementTieBreakPoints(player);
        if (isTieBrakeFinished(match, player)) {
            resetPlayersGames(match);
            incrementSets(player);
        }
    }


    /*
    Such method is applicable when the number of points between two players is 40
     */
    public void computeAdvantagePoints(Match match, Player playerOne, Player playerTwo) {
        int firstPlayerPoints = playerOne.getPlayerScore().getPoint().ordinal();
        int secondPlayerPoints = playerTwo.getPlayerScore().getPoint().ordinal();

        if (firstPlayerPoints != 0 && secondPlayerPoints != 0) {
            Player winner = (playerOne.getPlayerStatusUtil() == PlayerStatusUtil.POINT_WINNER) ? playerOne : playerTwo;
            Player loser = (winner == playerOne) ? playerTwo : playerOne;

            winner.getPlayerScore().setPoint(Point.ADVANTAGE);
            loser.getPlayerScore().setPoint(Point.FORTY);
            loser.setPlayerStatusUtil(PlayerStatusUtil.POINT_LOSER);
        } else {
            resetPlayersPoints(match);
        }
    }


    public void calculateAdvantagePoints(Player player) {
        if (player.getPlayerScore().getPoint() == Point.FORTY) {
            player.getPlayerScore().setPoint(Point.ADVANTAGE);
            player.setPlayerStatusUtil(PlayerStatusUtil.POINT_WINNER);
        } else {
            incrementGames(player);
            player.getPlayerScore().setPoint(Point.LOVE);
        }
    }

    public void incrementGames(Player player) {
        player.getPlayerScore().winGames();
    }

    public void incrementPoints(Player player) {
        if (player.getPlayerScore().getPoint() == Point.FORTY) {
            player.getPlayerScore().setPoint(Point.LOVE);
            incrementGames(player);
        } else {
            player.getPlayerScore().winPoint();
        }
    }

    public void incrementTieBreakPoints(Player player) {
        player.getPlayerScore().winTieBreakPoint();
    }


    public void incrementSets(Player player) {
        player.getPlayerScore().winSet();
    }


    public int getTieBreakPointsDifference(Match match) {
        return Math.abs(match.getPlayer1().getPlayerScore().getTieBreakPoint() - match.getPlayer2().getPlayerScore().getTieBreakPoint());
    }

    public void resetPlayersPoints(Match match) {
        match.getPlayer1().getPlayerScore().setPoint(Point.LOVE);
        match.getPlayer2().getPlayerScore().setPoint(Point.LOVE);
    }

    public void resetPlayersGames(Match match) {
        match.getPlayer1().getPlayerScore().setGame(0);
        match.getPlayer2().getPlayerScore().setGame(0);
    }


    private boolean isTieBrakeFinished(Match match, Player player) {
        return player.getPlayerScore().getTieBreakPoint() >= 7 && getTieBreakPointsDifference(match) >= 2;
    }

    private boolean isGameFinished(Match match, Player player) {
        return player.getPlayerScore().getTieBreakPoint() >= 4 && getTieBreakPointsDifference(match) >= 2;
    }
}
