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
    private int roundNumber = 0;
    private int turn = 0;
    private String playerNameTurn;

    public Game(String id, String gameName, Player creator) {
        this.id = id;
        this.gameName = gameName;
        this.creator = creator;
        this.playerNameTurn = creator.getPlayerName();
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
        int max_players = getJoinedPlayers().size();
        if(this.turn >= max_players-1)
            this.turn = 0;
        else
            this.turn += 1;
        this.playerNameTurn = joinedPlayers.get(this.turn).getPlayerName();
    }

    public String getPlayerNameTurn(){
       return this.playerNameTurn;
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }
    public int getTurn(){ return this.turn;}
}
