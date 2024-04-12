package by.bsuir.kostyademens.tennisscoreboard.controller;


import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/newMatch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO Стоит оставить такой вариант создания игрока, или же лучше сразу в конструкторе Player-а req.getParameter("player-1")
        String playerOne = req.getParameter("player-1");
        String playerTwo = req.getParameter("player-2");

        Player firstPlayer = new Player(playerOne.toUpperCase());
        Player secondPlayer = new Player(playerTwo.toUpperCase());



    }
}
