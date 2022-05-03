package com.tfg.game.entities.score;

import com.tfg.game.games.Game;
import com.tfg.game.games.GameJoiner;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

@Component
public class ScoreGameJoiner implements GameJoiner {

    private final ScoreFactory scoreFactory;

    public ScoreGameJoiner(ScoreFactory scoreFactory) {
        this.scoreFactory = scoreFactory;
    }

    @Override
    public void joinGame(Player owner, Game game) {
        scoreFactory.buildInventory(game, owner);
    }

}
