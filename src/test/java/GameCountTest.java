import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.model.Point;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameCountTest {

    private final MatchScoreCalculationService service = new MatchScoreCalculationService();
    private Match match;

    @BeforeEach
    public void setUp() {
        Player playerOne = new Player();
        Player playerTwo = new Player();
        match = Match.builder()
                .player1(playerOne)
                .player2(playerTwo)
                .build();
    }

    @Test
    public void matchSetsShouldIncrementCorrectly() {
        Player playerOne = match.getPlayer1();

        playerOne.getPlayerScore().setPoint(Point.THIRTY);

        service.incrementPoint(match, playerOne);
        assertEquals(0, playerOne.getPlayerScore().getGame());

        service.incrementPoint(match, playerOne);
        assertEquals(1, playerOne.getPlayerScore().getGame());


    }
}
