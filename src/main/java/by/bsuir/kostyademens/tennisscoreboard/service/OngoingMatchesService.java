package by.bsuir.kostyademens.tennisscoreboard.service;


import by.bsuir.kostyademens.tennisscoreboard.dto.MatchDto;
import by.bsuir.kostyademens.tennisscoreboard.model.Match;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private final Map<String, Match> ongoingMatches;

    public OngoingMatchesService() {
        ongoingMatches = new ConcurrentHashMap<>();
    }

    public String add(Match match) {
        String uuid = UUID.randomUUID().toString();
        while (ongoingMatches.containsKey(uuid)) uuid = UUID.randomUUID().toString();
        ongoingMatches.put(uuid, match);
        return uuid;
    }

    public Match get(String uuid) {
        return (ongoingMatches.getOrDefault(uuid, null));
    }

    public void remove(String uuid) {
        ongoingMatches.remove(uuid);
    }

}
