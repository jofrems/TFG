package com.tfg.game.ecs;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;

public interface GamePublicDataGenerator {
    void generatePublicData(GameData data, Game game, Player playingPlayer);
}
