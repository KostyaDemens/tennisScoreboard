package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.dao.PlayerDao;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;

public class NewMatchService {

  private final PlayerDao playerDao = new PlayerDao();

  private Player findOrCreatePlayer(Player player) {
    return playerDao.findByName(player.getName()).orElseGet(() -> playerDao.save(player));
  }

  public Match createNewMatch(Player playerOneName, Player playerTwoName) {
    Player playerOne = findOrCreatePlayer(playerOneName);
    Player playerTwo = findOrCreatePlayer(playerTwoName);
    return new Match(playerOne, playerTwo);
  }
}
