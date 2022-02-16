package com.drpicox.game.games.api;

import com.drpicox.game.common.api.GlobalRestException;

public class CreateGameForm {
    private String gameName;
    private String token;

    public CreateGameForm(String gameName, String token) {
        this.gameName = gameName;
        this.token = token;
    }

    public String getGameName() {
        return gameName;
    }

    public String getToken() {
        return token;
    }

    public void verify() {
        if (gameName.equals(""))
            throw new GlobalRestException("Game name should be present");
    }
}
