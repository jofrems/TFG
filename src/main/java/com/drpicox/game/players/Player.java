package com.drpicox.game.players;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Player {
    @Id private String playerName;
    private String password;
    private String token;

    public Player(String playerName, String password) {
        this.playerName = playerName;
        this.password = password;
        this.token = UUID.randomUUID().toString();
    }

    protected Player() {}

    public String getPlayerName() {
        return playerName;
    }

    public boolean isPasswordOk(String password) {
        boolean isPasswordOk = password.equals(this.password);
        return isPasswordOk;
    }

    public String getToken() {
        return this.token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
