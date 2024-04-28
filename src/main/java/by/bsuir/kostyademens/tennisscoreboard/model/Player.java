package by.bsuir.kostyademens.tennisscoreboard.model;

import by.bsuir.kostyademens.tennisscoreboard.util.PlayerStatusUtil;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Builder
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

    @Transient
    private PlayerScore playerScore = new PlayerScore();

    @Transient
    private PlayerStatusUtil playerStatus;

    public Player(String name) {
        this.name = name;
    }

}
