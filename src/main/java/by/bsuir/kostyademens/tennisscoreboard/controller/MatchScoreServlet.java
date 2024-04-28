package by.bsuir.kostyademens.tennisscoreboard.controller;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.service.MatchScoreCalculationService;
import by.bsuir.kostyademens.tennisscoreboard.service.OnGoingMatchesService;
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
        System.out.println(match.getPlayer1().getPlayerScore().getGame());
        req.getRequestDispatcher("/jsp/matchScore.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        UUID matchUUID = UUID.fromString(req.getParameter("uuid"));

    }
}
