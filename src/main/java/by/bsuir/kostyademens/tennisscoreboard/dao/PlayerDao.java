package by.bsuir.kostyademens.tennisscoreboard.dao;

import by.bsuir.kostyademens.tennisscoreboard.model.Player;
import by.bsuir.kostyademens.tennisscoreboard.util.SessionFactoryUtil;
import org.hibernate.Session;

public class PlayerDao {

    private final SessionFactoryUtil sessionFactoryUtil;

    public PlayerDao(SessionFactoryUtil sessionFactoryUtil) {
        this.sessionFactoryUtil = sessionFactoryUtil;
    }

    public boolean isPlayerExist(Player player) {
        try (Session session = sessionFactoryUtil.getSession()) {
            session.beginTransaction();
            Player existingPlayer = session.get(Player.class, player.getName());
            session.getTransaction().commit();
            return existingPlayer != null;
        }
    }
}
