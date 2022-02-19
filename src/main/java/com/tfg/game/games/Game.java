package com.tfg.game.games;

import com.tfg.game.players.Player;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game {
    @Id
    private String id;

    private String gameName;

    @ManyToOne
    private Player creator;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Player> joinedPlayers = new ArrayList<>();
    private int roundNumber = 1;

    public Game(String id, String gameName, Player creator) {
        this.id = id;
        this.gameName = gameName;
        this.creator = creator;
    }

    protected Game() {}

    public Player getCreator() {
        return creator;
    }

    public String getGameName() {
        return gameName;
    }

    public void joinPlayer(Player joiningPlayer) {
        joinedPlayers.add(joiningPlayer);
    }

    public boolean isPlayerJoined(Player player) {
        return joinedPlayers.contains(player);
    }

    public List<Player> getJoinedPlayers() {
        return this.joinedPlayers;
    }

    public void endRound() {
        this.roundNumber += 1;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }
}
