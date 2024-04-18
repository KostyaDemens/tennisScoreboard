package by.bsuir.kostyademens.tennisscoreboard.controller;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
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

    @Override
    public void init(ServletConfig config) {
        onGoingMatchesService = (OnGoingMatchesService) config.getServletContext().getAttribute("onGoingMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = onGoingMatchesService.get(uuid);

        //Атрибуты, который мы хотим получить в matchScore.jsp
        req.setAttribute("player-1", match.getPlayer1());
        req.setAttribute("player-2", match.getPlayer2());
        req.getRequestDispatcher("/jsp/matchScore.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}