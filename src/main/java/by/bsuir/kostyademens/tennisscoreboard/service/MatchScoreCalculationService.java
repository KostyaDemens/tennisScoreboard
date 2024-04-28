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
        
        incrementPoint(scoringPlayer);

        if (MatchStatusUtil.isTieBreak(match)) {
            countPointIfTieBreak(match, scoringPlayer);
        }
    }



    public void countPointIfTieBreak(Match match, Player scoringPlayer) {
        incrementTieBreakPoint(scoringPlayer);
        if (isTieBreakCompleted(match, scoringPlayer)) {
            resetPoints(match);
            resetGames(match);
            incrementSet(scoringPlayer);
            if (isMatchCompleted(scoringPlayer)) {
                match.setMatchStatus(MatchStatus.FINISHED);
            } else {
                match.setMatchStatus(MatchStatus.ONGOING);
            }
        }
    }

    public void countPointIfAdvantage(Match match, Player scoringPlayer, Player otherPlayer) {
        incrementAdvantagePoint(scoringPlayer);
        if (scoringPlayer.getPlayerStatus() == PlayerStatusUtil.POINT_WINNER) {
            otherPlayer.getPlayerScore().setPoint(Point.FORTY);
        } else {
            otherPlayer.getPlayerScore().setPoint(Point.FORTY);
        }
        if (isAdvantageCompleted(scoringPlayer)) {
            resetPoints(match);
            match.setMatchStatus(MatchStatus.ONGOING);
            incrementGame(scoringPlayer);
        }
        scoringPlayer.setPlayerStatus(PlayerStatusUtil.POINT_LOSER);
    }

    private boolean isMatchCompleted(Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getSet() ==2;
    }

    private boolean isTieBreakCompleted(Match match, Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getTieBreakPoint() >= 7 && getPointsDifference(match) >= 2;
    }

    private boolean isAdvantageCompleted(Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getPoint() == Point.LOVE;
    }

    private int getPointsDifference(Match match) {
        return Math.abs(match.getPlayer2().getPlayerScore().getPoint().getNumericValue() - match.getPlayer1().getPlayerScore().getPoint().getNumericValue());
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
            incrementGame(scoringPlayer);
        } else {
            scoringPlayer.getPlayerScore().winPoint();
        }
    }

    public void incrementTieBreakPoint(Player scoringPlayer) {
        scoringPlayer.getPlayerScore().winTieBreakPoint();
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
