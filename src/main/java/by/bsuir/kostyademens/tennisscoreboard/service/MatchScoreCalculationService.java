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
        Player losingPlayer = getLosingPlayer(match, player);


        checkIsAdvantage(match);
        if (MatchStatusUtil.isAdvantage(match)) {
            countPointIfAdvantage(match, scoringPlayer, losingPlayer);
            return;
        }

        checkIsTieBreak(match);
        if (MatchStatusUtil.isTieBreak(match)) {
            countPointIfTieBreak(match, scoringPlayer);
            return;
        }

        incrementPoint(match, scoringPlayer);




    }

    public void countPointIfTieBreak(Match match, Player scoringPlayer) {
        incrementTieBreakPoint(scoringPlayer);
        if (isTieBreakCompleted(match)) {
            scoringPlayer.getPlayerScore().winSet();
            resetTieBreakPoints(match);
            resetGames(match);
            if (isMatchCompleted(scoringPlayer)) {
                match.setWinner(scoringPlayer);
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

    public void checkIsTieBreak(Match match) {
        if (match.getPlayer1().getPlayerScore().getGame() == 6
                && match.getPlayer2().getPlayerScore().getGame() == 6) {
            match.setMatchStatus(MatchStatus.TIE_BREAK);
        }
    }

    public void countPointIfAdvantage(Match match, Player scoringPlayer, Player losingPlayer) {
        incrementAdvantagePoint(scoringPlayer, losingPlayer);
        if (isAdvantageCompleted(scoringPlayer)) {
            resetPoints(match);
            match.setMatchStatus(MatchStatus.ONGOING);
            if (isGameWin(match, scoringPlayer)) {
                scoringPlayer.getPlayerScore().winSet();
                resetGames(match);
                if (isMatchCompleted(scoringPlayer)) {
                    match.setWinner(scoringPlayer);
                    match.setMatchStatus(MatchStatus.FINISHED);
                }
            }
        }
    }


    private boolean isMatchCompleted(Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getSet() == 2;
    }

    private boolean isAdvantageCompleted(Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getPoint() == Point.LOVE;
    }

    private boolean isTieBreakCompleted(Match match) {
        if (match.getPlayer1().getPlayerScore().getTieBreakPoint() >= 7 || match.getPlayer2().getPlayerScore().getTieBreakPoint() >= 7) {
            return getTieBreakPointDifference(match) >= 2;
        }
        return false;
    }

    private int getPointsDifference(Player scoringPlayer, Player losingPlayer) {
        return Math.abs(scoringPlayer.getPlayerScore().getPoint().getNumericValue() - losingPlayer.getPlayerScore().getPoint().getNumericValue());
    }

    private int getTieBreakPointDifference(Match match) {
        return Math.abs(match.getPlayer1().getPlayerScore().getTieBreakPoint() - match.getPlayer2().getPlayerScore().getTieBreakPoint());
    }

    private int getGameDifference(Match match) {
        return Math.abs(match.getPlayer1().getPlayerScore().getGame() - match.getPlayer2().getPlayerScore().getGame());
    }

    private void resetPoints(Match match) {
        match.getPlayer1().getPlayerScore().setPoint(Point.LOVE);
        match.getPlayer2().getPlayerScore().setPoint(Point.LOVE);
    }

    private void resetGames(Match match) {
        match.getPlayer1().getPlayerScore().setGame(0);
        match.getPlayer2().getPlayerScore().setGame(0);
    }

    private void resetTieBreakPoints(Match match) {
        match.getPlayer1().getPlayerScore().setTieBreakPoint(0);
        match.getPlayer2().getPlayerScore().setTieBreakPoint(0);
    }

    private void incrementGame(Match match, Player scoringPlayer) {
        scoringPlayer.getPlayerScore().winGame();
        if (isGameWin(match, scoringPlayer)) {
            scoringPlayer.getPlayerScore().winSet();
            resetGames(match);
            if (isMatchCompleted(scoringPlayer)) {
                match.setWinner(scoringPlayer);
                match.setMatchStatus(MatchStatus.FINISHED);
            }
        }
    }

    // Оставил паблик, что бы можно было использовать в тестах
    public void incrementPoint(Match match, Player scoringPlayer) {
        if (scoringPlayer.getPlayerScore().getPoint() == Point.FORTY) {
            scoringPlayer.getPlayerScore().setPoint(Point.LOVE);
            resetPoints(match);
            incrementGame(match, scoringPlayer);
        } else {
            scoringPlayer.getPlayerScore().winPoint();
        }
    }

    private boolean isGameWin(Match match, Player scoringPlayer) {
        return scoringPlayer.getPlayerScore().getGame() >= 6 && getGameDifference(match) >= 2;
    }

    private void incrementTieBreakPoint(Player scoringPlayer) {
        scoringPlayer.getPlayerScore().winTieBreakPoint();
    }

    private void incrementAdvantagePoint(Player scoringPlayer, Player losingPlayer) {
        if (scoringPlayer.getPlayerScore().getPoint() == Point.ADVANTAGE) {
            scoringPlayer.getPlayerScore().setPoint(Point.LOVE);
            if (isAdvantageCompleted(scoringPlayer)) {
                scoringPlayer.getPlayerScore().winGame();
            }
        } else {
            if (getPointsDifference(scoringPlayer, losingPlayer) == 0) {
                scoringPlayer.getPlayerScore().setPoint(Point.ADVANTAGE);
                losingPlayer.getPlayerScore().setPoint(Point.FORTY);
            } else {
                scoringPlayer.getPlayerScore().setPoint(Point.FORTY);
                losingPlayer.getPlayerScore().setPoint(Point.FORTY);
            }
        }
    }

    private Player getScoringPlayer(Match match, PlayerNumber playerNum) {
        return switch (playerNum) {
            case FIRST_PLAYER -> match.getPlayer1();
            case SECOND_PLAYER -> match.getPlayer2();
        };
    }

    private Player getLosingPlayer(Match match, PlayerNumber playerNum) {
        return switch (playerNum) {
            case FIRST_PLAYER -> match.getPlayer2();
            case SECOND_PLAYER -> match.getPlayer1();
        };
    }

}
