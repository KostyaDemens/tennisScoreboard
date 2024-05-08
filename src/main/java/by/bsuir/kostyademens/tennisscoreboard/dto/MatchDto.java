package by.bsuir.kostyademens.tennisscoreboard.dto;


import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatus;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MatchDto {
    private PlayerDto playerOne;
    private PlayerDto playerTwo;
    private PlayerDto playerWinner;
    private MatchStatus status;
}

