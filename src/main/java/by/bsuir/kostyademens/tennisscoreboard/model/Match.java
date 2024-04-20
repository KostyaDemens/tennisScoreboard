package by.bsuir.kostyademens.tennisscoreboard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_1")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player_2")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Player winner;

    public Match() {
    }

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

}
