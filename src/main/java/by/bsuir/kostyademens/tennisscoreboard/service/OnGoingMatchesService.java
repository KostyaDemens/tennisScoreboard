package by.bsuir.kostyademens.tennisscoreboard.service;


import by.bsuir.kostyademens.tennisscoreboard.model.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OnGoingMatchesService {


    private final Map<UUID, Match> ongoingMatches;

    public OnGoingMatchesService() {
        ongoingMatches = new ConcurrentHashMap<>();
    }


    public UUID add(Match match) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, match);
        return uuid;
    }

    public Match get(UUID uuid) {
        return (ongoingMatches.get(uuid));
    }

    public void remove(UUID uuid) {
        ongoingMatches.remove(uuid);
    }

}
