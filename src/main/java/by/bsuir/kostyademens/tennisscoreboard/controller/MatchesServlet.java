package by.bsuir.kostyademens.tennisscoreboard.controller;

import by.bsuir.kostyademens.tennisscoreboard.dto.MatchDto;
import by.bsuir.kostyademens.tennisscoreboard.mapper.EntityMapper;
import by.bsuir.kostyademens.tennisscoreboard.service.FinishedMatchPersistenceService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

  private FinishedMatchPersistenceService finishedService;

  @Override
  public void init(ServletConfig config) {
    finishedService =
        (FinishedMatchPersistenceService)
            config.getServletContext().getAttribute("finishedMatchPersistenceService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String playerName = req.getParameter("filter_by_player_name");
    long noOfPage;
    int recordsPerPage = 5;
    int page = 1;
    if (req.getParameter("page") != null) {
      try {
        page = Integer.parseInt(req.getParameter("page"));
        if (page <= 0) {
          page = 1;
        }
      } catch (NumberFormatException ignored) {

      }
    }

    int offset = (page - 1) * recordsPerPage;

    List<MatchDto> matches;
    if (playerName == null || playerName.isEmpty()) {
      matches =
          finishedService.selectMatches(offset, recordsPerPage).stream()
              .map(EntityMapper::toDto)
              .collect(Collectors.toList());
      noOfPage = (int) Math.ceil(finishedService.getTotalMatchesCount() * 1.0 / recordsPerPage);
    } else {
      playerName = playerName.toUpperCase();
      matches =
          finishedService.selectMatchesFilteredByName(playerName, offset, recordsPerPage).stream()
              .map(EntityMapper::toDto)
              .collect(Collectors.toList());
      req.setAttribute("filter_by_player_name", playerName);
      noOfPage =
          (int)
              Math.ceil(
                  finishedService.getTotalMatchesCountByName(playerName) * 1.0 / recordsPerPage);
    }

    req.setAttribute("noOfPage", noOfPage);
    req.setAttribute("currentPage", page);
    req.setAttribute("matches", matches);

    req.getRequestDispatcher("/jsp/matchesHistory.jsp").forward(req, resp);
  }
}
