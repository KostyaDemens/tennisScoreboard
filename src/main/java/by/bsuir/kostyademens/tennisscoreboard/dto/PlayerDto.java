package by.bsuir.kostyademens.tennisscoreboard.dto;

import by.bsuir.kostyademens.tennisscoreboard.model.PlayerScore;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private String playerName;
    private PlayerScoreDto playerScore;
}

