package by.bsuir.kostyademens.tennisscoreboard.mapper;

import by.bsuir.kostyademens.tennisscoreboard.dto.MatchDto;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;

import java.util.function.Function;

public class MatchDtoMapper implements Function<Match, MatchDto> {

    @Override
    public MatchDto apply(Match match) {
        return new MatchDto(
//                match.getId(),
                match.getPlayer1().getName(),
                match.getPlayer2().getName()
        );
    }
}
