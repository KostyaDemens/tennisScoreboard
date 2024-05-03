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

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private FinishedMatchPersistenceService finishedService;

    @Override
    public void init(ServletConfig config) {
        finishedService = (FinishedMatchPersistenceService) config.getServletContext().getAttribute("finishedMatchPersistenceService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/jsp/matchesHistory.jsp").forward(req, resp);
    }
}
