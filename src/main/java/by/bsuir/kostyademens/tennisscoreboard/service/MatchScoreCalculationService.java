package by.bsuir.kostyademens.tennisscoreboard.service;


import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatus;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatusUtil;
import by.bsuir.kostyademens.tennisscoreboard.util.PlayerNumber;
import by.bsuir.kostyademens.tennisscoreboard.util.PlayerStatusUtil;

public class MatchScoreCalculationService {

    public void makeCalculations(Match match, PlayerNumber player) {
        if (MatchStatusUtil.isMatchFinished(match)) {
            return;
        }

        Player scoringPlayer = getScoringPlayer(match, player);
        Player otherPlayer = getScoringPlayer(match, player);
        if (MatchStatusUtil.isAdvantage(match)) {
            countPointIfAdvantage(match, scoringPlayer, otherPlayer);
            return;
        }
        incrementPoint(scoringPlayer);
    }


    public void countPointIfAdvantage(Match match, Player scoringPlayer, Player otherPlayer) {
        incrementAdvantagePoint(scoringPlayer);
        if (scoringPlayer.getPlayerStatus() == PlayerStatusUtil.POINT_WINNER) {
            otherPlayer.getPlayerScore().setPoint(Point.FORTY);
        } else {
            otherPlayer.getPlayerScore().setPoint(Point.FORTY);
        }
        if (isAdvantageCompleted(match, scoringPlayer)) {
            resetPoints(match);
            match.setMatchStatus(MatchStatus.ONGOING);
            incrementGame(scoringPlayer);
        }
        scoringPlayer.setPlayerStatus(PlayerStatusUtil.POINT_LOSER);
    }

    private boolean isGameCompleted(Match match, Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getPoint().ordinal() >= 4 && getPointsDifference(match) >= 2;
    }

    private boolean isTieBreakCompleted(Match match, Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getTieBreakPoint() >= 7 && getPointsDifference(match) >= 2;
    }

    private boolean isAdvantageCompleted(Match match, Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getPoint() == Point.LOVE;
    }

    private int getPointsDifference(Match match) {
        return Math.abs(match.getPlayer2().getPlayerScore().getPoint().getNumericValue() - match.getPlayer1().getPlayerScore().getPoint().getNumericValue());
    }

    private int getGamesDifference(Match match) {
        return Math.abs(match.getPlayer2().getPlayerScore().getGame() - match.getPlayer1().getPlayerScore().getGame());
    }

    private void resetPoints(Match match) {
        match.getPlayer1().getPlayerScore().setPoint(Point.LOVE);
        match.getPlayer2().getPlayerScore().setPoint(Point.LOVE);
    }

    private void resetGames(Match match) {
        match.getPlayer1().getPlayerScore().setGame(0);
        match.getPlayer2().getPlayerScore().setGame(0);
    }

    private void incrementSet(Player scoringPlayer) {
        scoringPlayer.getPlayerScore().winSet();
    }

    private void incrementGame(Player scoringPlayer) {
        scoringPlayer.getPlayerScore().winGame();
    }

    public void incrementPoint(Player scoringPlayer) {
        if (scoringPlayer.getPlayerScore().getPoint() == Point.FORTY) {
            scoringPlayer.getPlayerScore().setPoint(Point.LOVE);
        } else {
            scoringPlayer.getPlayerScore().winPoint();
        }
    }

    public void incrementAdvantagePoint(Player scoringPlayer) {
        if (scoringPlayer.getPlayerScore().getPoint() == Point.FORTY) {
            scoringPlayer.getPlayerScore().setPoint(Point.ADVANTAGE);
            scoringPlayer.setPlayerStatus(PlayerStatusUtil.POINT_WINNER);
        } else {
            scoringPlayer.getPlayerScore().setPoint(Point.LOVE);
        }
    }

    private Player getScoringPlayer(Match match, PlayerNumber playerNum) {
        return switch (playerNum) {
            case FIRST_PLAYER -> match.getPlayer1();
            case SECOND_PLAYER -> match.getPlayer2();
        };
    }

}
