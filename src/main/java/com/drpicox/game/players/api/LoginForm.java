package com.drpicox.game.players.api;

public class LoginForm {
    private String playerName;
    private String password;

    public LoginForm(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getPassword() {
        return this.password;
    }
}
