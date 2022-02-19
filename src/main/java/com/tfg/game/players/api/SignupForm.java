package com.tfg.game.players.api;

import com.tfg.game.common.api.GlobalRestException;

public class SignupForm {

    private String playerName;
    private String password;

    public SignupForm(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPassword() {
        return this.password;
    }

    public void verify() {
        if (playerName.equals(""))
            throw new GlobalRestException("Player name should be present");
        if (playerName.length() < 3)
            throw new GlobalRestException("Player name should be at least three characters long");

        if (password.equals(""))
            throw new GlobalRestException("Password should be present");
        if (password.length() < 6)
            throw new GlobalRestException("Player password should be at least six characters long");
        if (!password.matches(".*\\d.*"))
            throw new GlobalRestException("Player password should have at least one number");
        if (!password.matches(".*[A-Za-z].*"))
            throw new GlobalRestException("Player password should have at least one letter");
    }
}
