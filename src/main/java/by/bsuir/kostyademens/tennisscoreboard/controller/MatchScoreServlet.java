package by.bsuir.kostyademens.tennisscoreboard.controller;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import by.bsuir.kostyademens.tennisscoreboard.service.OnGoingMatchesService;
import by.bsuir.kostyademens.tennisscoreboard.util.PlayerNumber;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private OnGoingMatchesService onGoingMatchesService;
    private MatchScoreCalculationService calculationService;

    private static final String PLAYER_ID = "1";


    @Override
    public void init(ServletConfig config) {
        onGoingMatchesService = (OnGoingMatchesService) config.getServletContext().getAttribute("onGoingMatchesService");
        calculationService = (MatchScoreCalculationService) config.getServletContext().getAttribute("matchScoreCalculationService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = onGoingMatchesService.get(uuid);
        req.setAttribute("playerOne", match.getPlayer1());
        req.setAttribute("playerTwo", match.getPlayer2());
        req.getRequestDispatcher("/jsp/matchScore.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String player_id = req.getParameter("player_id");

        Match match = onGoingMatchesService.get(uuid);
        PlayerNumber playerNumber = getPlayerNumber(player_id);

        calculationService.makeCalculations(match, playerNumber);

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }

    private PlayerNumber getPlayerNumber(String player_id) {
        return player_id.equals(PLAYER_ID) ? PlayerNumber.FIRST_PLAYER : PlayerNumber.SECOND_PLAYER;
    }
}
