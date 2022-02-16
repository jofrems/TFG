package com.drpicox.game.games.api;

import java.util.List;

public class ListGamesResponseEntry {
    private String creatorName;
    private String gameName;
    private List<String> joinedPlayerNames;

    public ListGamesResponseEntry(String gameName, String creatorName, List<String> joinedPlayerNames) {
        this.creatorName = creatorName;
        this.gameName = gameName;
        this.joinedPlayerNames = joinedPlayerNames;
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public List<String> getJoinedPlayerNames() {
        return this.joinedPlayerNames;
    }
}
