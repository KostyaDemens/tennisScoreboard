package by.bsuir.kostyademens.tennisscoreboard.controller;

import by.bsuir.kostyademens.tennisscoreboard.dto.MatchDto;
import by.bsuir.kostyademens.tennisscoreboard.mapper.EntityMapper;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.service.FinishedMatchPersistenceService;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import by.bsuir.kostyademens.tennisscoreboard.service.OnGoingMatchesService;
import by.bsuir.kostyademens.tennisscoreboard.util.MatchStatus;
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
    private FinishedMatchPersistenceService persistenceService;
    private static final String PLAYER_ID = "1";


    @Override
    public void init(ServletConfig config) {
        onGoingMatchesService = (OnGoingMatchesService) config.getServletContext().getAttribute("onGoingMatchesService");
        calculationService = (MatchScoreCalculationService) config.getServletContext().getAttribute("matchScoreCalculationService");
        persistenceService = (FinishedMatchPersistenceService) config.getServletContext().getAttribute("finishedMatchPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = onGoingMatchesService.get(uuid);
        MatchDto matchDto = EntityMapper.toDto(match);
        req.setAttribute("match", matchDto);
        req.getRequestDispatcher("/jsp/matchScore.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        String player_id = req.getParameter("player_id");

        Match match = onGoingMatchesService.get(uuid);

        PlayerNumber playerNumber = getPlayerNumber(player_id);


        calculationService.makeCalculations(match, playerNumber);

        if (match.getMatchStatus() == MatchStatus.FINISHED) {
            persistenceService.saveMatch(match);
        }

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }

    private PlayerNumber getPlayerNumber(String player_id) {
        return player_id.equals(PLAYER_ID) ? PlayerNumber.FIRST_PLAYER : PlayerNumber.SECOND_PLAYER;
    }
}
