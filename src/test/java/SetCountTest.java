import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import static org.junit.jupiter.api.Assertions.assertEquals;

import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetCountTest {

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
    public void matchSetsShouldIncrementCorrectly() {
        Player playerOne = match.getPlayer1();

        playerOne.getPlayerScore().setPoint(Point.ADVANTAGE);
        matchScoreCalculationService.calculatePoints(playerOne);

        assertEquals(1, playerOne.getPlayerScore().getSets());


    }
}
