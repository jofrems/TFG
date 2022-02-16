package com.drpicox.game.games.api;

import com.drpicox.game.games.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListGamesResponse {

    private List<ListGamesResponseEntry> games = new ArrayList<>();

    public List<ListGamesResponseEntry> getGames() {
        return games;
    }

    public void addGame(Game game) {
        String gameName = game.getGameName();
        String playerName = game.getCreator().getPlayerName();
        var joinedPlayerNames = game.getJoinedPlayers().stream()
                .map(p -> p.getPlayerName())
                .collect(Collectors.toList());

        var entry = new ListGamesResponseEntry(gameName, playerName, joinedPlayerNames);
        games.add(entry);
    }
}
