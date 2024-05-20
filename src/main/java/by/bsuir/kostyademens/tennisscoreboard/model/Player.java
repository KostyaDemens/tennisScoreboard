package by.bsuir.kostyademens.tennisscoreboard.model;

import by.bsuir.kostyademens.tennisscoreboard.util.PlayerNumber;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "players")
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Transient private PlayerScore playerScore = new PlayerScore();

  @Transient private PlayerNumber playerNumber;

  public Player(String name) {
    this.name = name;
  }
}
