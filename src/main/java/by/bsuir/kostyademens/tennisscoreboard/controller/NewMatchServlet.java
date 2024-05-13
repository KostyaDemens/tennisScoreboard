package by.bsuir.kostyademens.tennisscoreboard.controller;


import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.service.NewMatchService;
import by.bsuir.kostyademens.tennisscoreboard.service.OnGoingMatchesService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private NewMatchService newMatchService;
    private OnGoingMatchesService onGoingMatchesService;

    @Override
    public void init(ServletConfig config) {
        newMatchService = (NewMatchService) config.getServletContext().getAttribute("newMatchService");
        onGoingMatchesService = (OnGoingMatchesService) config.getServletContext().getAttribute("onGoingMatchesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/newMatch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Эти параметры мы получаем из newMatch.jsp страницы, используем их для создания объекта Player
        String playerOne = req.getParameter("player-1");
        String playerTwo = req.getParameter("player-2");

        Player firstPlayer = new Player(playerOne.toUpperCase());
        Player secondPlayer = new Player(playerTwo.toUpperCase());

        if (isNameValid(firstPlayer.getName()) || isNameValid(secondPlayer.getName()) || firstPlayer.getName().equals(secondPlayer.getName())) {
            req.setAttribute("errorMessage", "Некорректное имя игрока. Оно не должно содержать никаких символов, кроме символов русского и английского алфавита");
            req.getRequestDispatcher("/jsp/newMatch.jsp").forward(req, resp);
            return;
        }

        // Генерируем UUID и кладём матч в коллекцию матчей
        Match match = newMatchService.createNewMatch(firstPlayer, secondPlayer);
        UUID uuid = onGoingMatchesService.add(match);

        // Редирект на страницу /match-score?uuid=uuid
        resp.sendRedirect(req.getContextPath() + "match-score?uuid=" + uuid);


    }

    private boolean isNameValid(String name) {
        if (name == null) {
            return true;
        }
        return !name.matches("^[a-zA-Zа-яА-Я]+$");
    }
}
