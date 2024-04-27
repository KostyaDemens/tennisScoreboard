import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultPointCountTest {
    private final MatchScoreCalculationService service = new MatchScoreCalculationService();
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
        Player playerOne = match.getPlayer1();

        service.incrementPoint(playerOne);
        assertEquals(15, playerOne.getPlayerScore().getPoint().getNumericValue());

        service.incrementPoint(playerOne);
        assertEquals(30, playerOne.getPlayerScore().getPoint().getNumericValue());

        service.incrementPoint(playerOne);
        assertEquals(40, playerOne.getPlayerScore().getPoint().getNumericValue());

        service.incrementPoint(playerOne);
        assertEquals(0, playerOne.getPlayerScore().getPoint().getNumericValue());

        service.incrementPoint(playerOne);
        assertEquals(15, playerOne.getPlayerScore().getPoint().getNumericValue());
    }
}
