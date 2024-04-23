import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PointCountTest {
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private Match match;

    @BeforeEach
    public void setUp() {
        Player playerOne = new Player();
        Player playerTwo = new Player();
        match = Match.builder()
                .maxMatchSets(2)
                .player1(playerOne)
                .player2(playerTwo)
                .build();
    }

    @Test
    public void playerPointsShouldIncrementCorrectly() {
        Player player1 = match.getPlayer1();

        matchScoreCalculationService.incrementPoints(player1);
        assertEquals(15, player1.getPlayerScore().getPoints());

        matchScoreCalculationService.incrementPoints(player1);
        assertEquals(30, player1.getPlayerScore().getPoints());

        matchScoreCalculationService.incrementPoints(player1);
        assertEquals(40, player1.getPlayerScore().getPoints());


    }
}
