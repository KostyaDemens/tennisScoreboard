package by.bsuir.kostyademens.tennisscoreboard.util;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;

public class MatchStatusUtil {

    public static boolean isMatchFinished(Match match) {
        return match.getMatchStatus() == MatchStatus.FINISHED;
    }

    public static boolean isTieBreak(Match match) {
        return match.getMatchStatus() == MatchStatus.TIE_BREAK;
    }

}
