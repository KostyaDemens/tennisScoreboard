package by.bsuir.kostyademens.tennisscoreboard.controller;

import by.bsuir.kostyademens.tennisscoreboard.dto.MatchDto;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.service.FinishedMatchPersistenceService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private FinishedMatchPersistenceService finishedService;

    @Override
    public void init(ServletConfig config) {
        finishedService = (FinishedMatchPersistenceService) config.getServletContext().getAttribute("finishedMatchPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerName = req.getParameter("filter_by_player_name");
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<MatchDto> matches;


        if (playerName == null || playerName.isEmpty()) {
            matches = finishedService.selectAllMatches((page - 1) * recordsPerPage, recordsPerPage);

        } else {
            playerName = playerName.toUpperCase();
            matches = finishedService.filterMatchesByName(playerName, (page - 1) * recordsPerPage, recordsPerPage);
            req.setAttribute("filter_by_player_name", playerName);
        }
        int noOfRecords = finishedService.getNoOfRecords();

        //Определяем сколько нам нужно страниц
        int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("noOfPage", noOfPage);
        req.setAttribute("currentPage", page);
        req.setAttribute("matches", matches);

        req.getRequestDispatcher("/jsp/matchesHistory.jsp").forward(req, resp);
    }
}

