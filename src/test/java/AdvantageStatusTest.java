import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AdvantageStatusTest {

    private final MatchScoreCalculationService service = new MatchScoreCalculationService();
    private Match match;

    @BeforeEach
    public void setUp() {
        Player playerOne = new Player("Vova");
        Player playerTwo = new Player("Petya");
        match = Match.builder()
                .maxMatchSets(2)
                .player1(playerOne)
                .player2(playerTwo)
                .build();
    }

    @Test
    public void pointsShouldIncrementCorrectlyWhileMatchStatusIsAdvantage() {
        Player playerOne = match.getPlayer1();
        Player playerTwo = match.getPlayer2();

        playerOne.getPlayerScore().setPoint(Point.FORTY);
        playerTwo.getPlayerScore().setPoint(Point.FORTY);


        service.countPointIfAdvantage(match, playerTwo, playerOne);
        assertEquals(40, playerOne.getPlayerScore().getPoint().getNumericValue());
        assertEquals("AD", playerTwo.getPlayerScore().getPoint().toString());

        service.countPointIfAdvantage(match, playerOne, playerTwo);
        assertEquals(40, playerOne.getPlayerScore().getPoint().getNumericValue());
        assertEquals(40, playerTwo.getPlayerScore().getPoint().getNumericValue());

        service.countPointIfAdvantage(match, playerTwo, playerOne);
        assertEquals(40, playerOne.getPlayerScore().getPoint().getNumericValue());
        assertEquals("AD", playerTwo.getPlayerScore().getPoint().toString());

        service.countPointIfAdvantage(match, playerOne, playerTwo);
        assertEquals(40, playerOne.getPlayerScore().getPoint().getNumericValue());
        assertEquals(40, playerTwo.getPlayerScore().getPoint().getNumericValue());

        service.countPointIfAdvantage(match, playerOne, playerTwo);
        assertEquals("AD", playerOne.getPlayerScore().getPoint().toString());
        assertEquals(40, playerTwo.getPlayerScore().getPoint().getNumericValue());

        service.countPointIfAdvantage(match, playerOne, playerTwo);
        assertEquals(0, playerOne.getPlayerScore().getPoint().getNumericValue());
        assertEquals(0, playerOne.getPlayerScore().getPoint().getNumericValue());

    }
}
