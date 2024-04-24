package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatusUtil;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.util.PlayerNumberUtil;


public class MatchScoreCalculationService {


    public void startAdvantageRound(Player winner, Player loser) {

    }

    public void incrementPoints(Player player) {
        player.getPlayerScore().winPoint();
    }


    private Player getScoringPlayer(Match match, PlayerNumberUtil playerNumber) {
        return switch (playerNumber) {
            case FIRST_PLAYER -> match.getPlayer1();
            case SECOND_PLAYER -> match.getPlayer2();
        };
    }

    private boolean isTieBrake(Match match) {
        return match.getMatchStatus() == MatchStatusUtil.TIE_BRAKE;
    }

    private boolean isMatchFinished(Match match) {
        return match.getMatchStatus() == MatchStatusUtil.FINISHED;
    }
}
