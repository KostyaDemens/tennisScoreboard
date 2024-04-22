package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatusUtil;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.util.PlayerNumberUtil;

public class MatchScoreCalculationService {




    public void incrementPoints(Player player) {
        int currentPoints = player.getPlayerScore().getPoints();
        if (currentPoints == 0) {
            player.getPlayerScore().setPoints(15);
        } else if (currentPoints == 15) {
            player.getPlayerScore().setPoints(30);
        } else if (currentPoints == 30) {
            player.getPlayerScore().setPoints(40);
        } else if (currentPoints == 40) {
            // Проверка преимущества и т.д
            player.getPlayerScore().setPoints(0);
        }
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
