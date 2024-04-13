package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;


public class NewMatchService {

    private final PlayerService playerService = new PlayerService();

    public Match createNewMatch(Player playerOneName, Player playerTwoName) {
        Player playerOne = playerService.findOrCreatePlayer(playerOneName);
        Player playerTwo = playerService.findOrCreatePlayer(playerTwoName);
        return new Match(playerOne, playerTwo);
    }
}
