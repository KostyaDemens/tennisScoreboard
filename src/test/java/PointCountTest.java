import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Player playerOne = match.getPlayer1();

        matchScoreCalculationService.incrementPoints(playerOne);
        assertEquals(15, playerOne.getPlayerScore().getPoint().getNumericValue());

        matchScoreCalculationService.incrementPoints(playerOne);
        assertEquals(30, playerOne.getPlayerScore().getPoint().getNumericValue());

        matchScoreCalculationService.incrementPoints(playerOne);
        assertEquals(40, playerOne.getPlayerScore().getPoint().getNumericValue());
    }

    @Test
    public void advantageCountPointShouldWorkCorrectly() {
        Player playerOne = match.getPlayer1();
        Player playerTwo = match.getPlayer2();

        playerOne.getPlayerScore().setPoint(Point.FORTY);
        playerTwo.getPlayerScore().setPoint(Point.FORTY);

        matchScoreCalculationService.incrementPoints(playerTwo);
        matchScoreCalculationService.advantageCountPoint(playerOne,playerTwo);

        assertEquals("40", playerOne.getPlayerScore().getPoint().toString());
        assertEquals("AD", playerTwo.getPlayerScore().getPoint().toString());

        matchScoreCalculationService.incrementPoints(playerOne);
        matchScoreCalculationService.advantageCountPoint(playerOne, playerTwo);

        assertEquals("AD", playerOne.getPlayerScore().getPoint().toString());
        assertEquals("40", playerTwo.getPlayerScore().getPoint().toString());

        matchScoreCalculationService.incrementPoints(playerOne);
        matchScoreCalculationService.advantageCountPoint(playerOne, playerTwo);

        assertEquals("0", playerOne.getPlayerScore().getPoint().toString());
        assertEquals("0", playerTwo.getPlayerScore().getPoint().toString());
    }


}
