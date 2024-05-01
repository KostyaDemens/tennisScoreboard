import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TieBreakStatusTest {

    private final MatchScoreCalculationService service = new MatchScoreCalculationService();
    private Match match;

    @BeforeEach
    public void setUp() {
        Player playerOne = new Player("Vova");
        Player playerTwo = new Player("Petya");
        match = Match.builder()
                .player1(playerOne)
                .player2(playerTwo)
                .build();
    }


    @Test
    public void tieBreakPointsShouldIncrementCorrectlyWhenMatchStatusIsTieBreak() {
        Player firstPlayer = match.getPlayer1();
        Player secondPlayer = match.getPlayer2();

        firstPlayer.getPlayerScore().setGame(6);
        secondPlayer.getPlayerScore().setGame(6);

        service.countPointIfTieBreak(match, firstPlayer);
        assertEquals(1, firstPlayer.getPlayerScore().getPoint().ordinal());

        service.countPointIfTieBreak(match, secondPlayer);
        assertEquals(1, secondPlayer.getPlayerScore().getPoint().ordinal());
    }
}
