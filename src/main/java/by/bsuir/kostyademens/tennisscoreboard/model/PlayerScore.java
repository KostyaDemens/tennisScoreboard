package by.bsuir.kostyademens.tennisscoreboard.model;

import by.bsuir.kostyademens.tennisscoreboard.util.PointNumberUtil;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerScore {
    private int points;
    private int games;
    private int sets;
    private PointNumberUtil pointNumberUtil;
}
