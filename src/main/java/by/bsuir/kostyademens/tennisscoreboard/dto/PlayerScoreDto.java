package by.bsuir.kostyademens.tennisscoreboard.dto;

import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerScoreDto {
  private Point point;
  private int set;
  private int game;
  private int tieBreakPoint;
}
