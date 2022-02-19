package com.tfg.game.games.api;

import com.tfg.game.common.api.GlobalRestException;

public class ListGamesByCreatorForm {
    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void verify() {
        if (playerName.equals(""))
            throw new GlobalRestException("The friend name should be present");
    }
}
