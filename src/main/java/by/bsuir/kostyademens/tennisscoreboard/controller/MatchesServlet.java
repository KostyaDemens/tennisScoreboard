package by.bsuir.kostyademens.tennisscoreboard.controller;

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
        int page = 1;
        int recordsPerPage = 5;
        if (req.getParameter("page") != null) {
            page = Integer.parseInt(req.getParameter("page"));
        }

        List<Match> matches = finishedService.selectAllMatches((page - 1)*recordsPerPage, recordsPerPage);
        int noOfRecords = finishedService.getNoOfRecords();

        //Определяем сколько нам нужно страниц
        int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        req.setAttribute("matches", matches);
        req.setAttribute("noOfPage", noOfPage);
        req.setAttribute("currentPage", page);

        req.getRequestDispatcher("/jsp/matchesHistory.jsp").forward(req, resp);
    }
}
