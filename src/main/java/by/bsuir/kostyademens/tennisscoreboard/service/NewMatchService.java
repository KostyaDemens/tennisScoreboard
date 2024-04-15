package by.bsuir.kostyademens.tennisscoreboard.service;

import by.bsuir.kostyademens.tennisscoreboard.dao.PlayerDao;
import by.bsuir.kostyademens.tennisscoreboard.dto.MatchDto;
import by.bsuir.kostyademens.tennisscoreboard.dto.PlayerDto;
import by.bsuir.kostyademens.tennisscoreboard.mapper.ModelMapper;
import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;

import java.util.List;


public class NewMatchService {

    private final SessionFactoryUtil factoryUtil = new SessionFactoryUtil();
    PlayerDao playerDao = new PlayerDao(factoryUtil);

    public PlayerDto findOrCreatePlayer(Player player) {
        return ModelMapper.INSTANCE.toDto(playerDao.findByName(player.getName())
                .orElseGet(() -> playerDao.save(player)));
    }

    public MatchDto createNewMatch(Player playerOneName, Player playerTwoName) {
        PlayerDto playerOne = findOrCreatePlayer(playerOneName);
        PlayerDto playerTwo = findOrCreatePlayer(playerTwoName);
        return new MatchDto(playerOne, playerTwo);
    }

}
