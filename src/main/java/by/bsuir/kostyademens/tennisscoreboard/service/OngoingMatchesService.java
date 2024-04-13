package by.bsuir.kostyademens.tennisscoreboard.service;


import by.bsuir.kostyademens.tennisscoreboard.model.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class OngoingMatchesService {

    private final Map<String, Match> ongoingMatches;

    public OngoingMatchesService() {
        ongoingMatches = new HashMap<>();
    }

    public String add(Match match) {
        String uuid = UUID.randomUUID().toString();
        while (ongoingMatches.containsKey(uuid)) uuid = UUID.randomUUID().toString();
        ongoingMatches.put(uuid, match);
        return uuid;
    }

    public Optional<Match> get(String uuid) {
        return ongoingMatches.containsKey(uuid) ? Optional.of(ongoingMatches.get(uuid)) : Optional.empty();
    }

    public void remove(String uuid) {
        ongoingMatches.remove(uuid);
    }

}
