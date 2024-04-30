package by.bsuir.kostyademens.tennisscoreboard.service;


import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatus;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatusUtil;
import by.bsuir.kostyademens.tennisscoreboard.util.PlayerNumber;

public class MatchScoreCalculationService {

    public void makeCalculations(Match match, PlayerNumber player) {
        if (MatchStatusUtil.isMatchFinished(match)) {
            return;
        }

        Player scoringPlayer = getScoringPlayer(match, player);
        Player losingPlayer;


        if (player == PlayerNumber.SECOND_PLAYER) {
            losingPlayer = getFirstPlayer(match);
        } else {
            losingPlayer = getSecondPlayer(match);
        }

        checkIsAdvantage(match);
        if (MatchStatusUtil.isAdvantage(match)) {
            countPointIfAdvantage(match, scoringPlayer, losingPlayer);
            return;
        }
        incrementPoint(scoringPlayer);


        if (scoringPlayer.getPlayerScore().getPoint() == Point.LOVE) {
            resetPoints(match);
        }

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

    public void checkIsAdvantage(Match match) {
        if (match.getPlayer1().getPlayerScore().getPoint() == Point.FORTY
                && match.getPlayer2().getPlayerScore().getPoint() == Point.FORTY) {
            match.setMatchStatus(MatchStatus.ADVANTAGE);
        }
    }

    public void countPointIfAdvantage(Match match, Player scoringPlayer, Player losingPlayer) {
        incrementAdvantagePoint(scoringPlayer, losingPlayer);
        if (isAdvantageCompleted(scoringPlayer)) {
            match.setMatchStatus(MatchStatus.ONGOING);
            resetPoints(match);
        }
    }


    private boolean isMatchCompleted(Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getSet() == 2;
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

    public void incrementAdvantagePoint(Player scoringPlayer, Player losingPlayer) {
        if (scoringPlayer.getPlayerScore().getPoint() == Point.ADVANTAGE) {
            scoringPlayer.getPlayerScore().setPoint(Point.LOVE);
            if (isAdvantageCompleted(scoringPlayer)) {
                scoringPlayer.getPlayerScore().winGame();
            }
            return;
        }
        scoringPlayer.getPlayerScore().setPoint(Point.ADVANTAGE);

        losingPlayer.getPlayerScore().setPoint(Point.FORTY);

    }

    private Player getScoringPlayer(Match match, PlayerNumber playerNum) {
        return switch (playerNum) {
            case FIRST_PLAYER -> match.getPlayer1();
            case SECOND_PLAYER -> match.getPlayer2();
        };
    }

    private Player getFirstPlayer(Match match) {
        return match.getPlayer1();
    }

    private Player getSecondPlayer(Match match) {
        return match.getPlayer2();
    }

}
