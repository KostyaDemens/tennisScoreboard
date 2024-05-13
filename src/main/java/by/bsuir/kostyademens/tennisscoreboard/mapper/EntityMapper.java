package by.bsuir.kostyademens.tennisscoreboard.mapper;

import by.bsuir.kostyademens.tennisscoreboard.dto.MatchDto;
import by.bsuir.kostyademens.tennisscoreboard.dto.PlayerDto;
import by.bsuir.kostyademens.tennisscoreboard.dto.PlayerScoreDto;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.PlayerScore;


public class EntityMapper {

    public static MatchDto toDto(Match match) {
        if (match == null) {
            return null;
        }

        PlayerDto playerOneDto = toDto(match.getPlayer1());
        PlayerDto playerTwoDto = toDto(match.getPlayer2());
        PlayerDto playerWinnerDto = toDto(match.getWinner());

        return MatchDto.builder()
                .playerOne(playerOneDto)
                .playerTwo(playerTwoDto)
                .playerWinner(playerWinnerDto)
                .status(match.getMatchStatus())
                .build();
    }

    public static PlayerDto toDto(Player player) {
        if (player == null) {
            return null;
        }

        PlayerScoreDto playerScoreDto = toDto(player.getPlayerScore());
        return PlayerDto.builder()
                .playerName(player.getName())
                .playerScore(playerScoreDto)
                .build();
    }

    private static PlayerScoreDto toDto(PlayerScore playerScore) {
        if (playerScore == null) {
            return null;
        }
        return PlayerScoreDto.builder()
                .point(playerScore.getPoint())
                .set(playerScore.getSet())
                .game(playerScore.getGame())
                .tieBreakPoint(playerScore.getTieBreakPoint())
                .build();
    }
}
