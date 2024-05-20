package by.bsuir.kostyademens.tennisscoreboard.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "matches")
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "player_1", nullable = false)
  private Player player1;

  @ManyToOne
  @JoinColumn(name = "player_2", nullable = false)
  private Player player2;

  @ManyToOne
  @JoinColumn(name = "winner", nullable = false)
  private Player winner;

  @Transient @Builder.Default private MatchStatus matchStatus = MatchStatus.ONGOING;

  public Match(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
  }
}
