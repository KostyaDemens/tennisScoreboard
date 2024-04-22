package by.bsuir.kostyademens.tennisscoreboard.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerScore {
    private int points;
    private int games;
    private int sets;
}
