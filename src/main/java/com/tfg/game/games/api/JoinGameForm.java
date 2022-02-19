package com.tfg.game.games.api;

public class JoinGameForm {
    private String gameName;
    private String creatorName;
    private String token;

    public JoinGameForm(String gameName, String creatorName, String token) {
        this.gameName = gameName;
        this.creatorName = creatorName;
        this.token = token;
    }

    public String getGameName() {
        return gameName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getToken() {
        return token;
    }
}
