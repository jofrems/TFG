package com.tfg.game.ecs;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;

public interface GameReachableDataGenerator {
    void generateReachableData(GameData data, Game game, Player playingPlayer);
}
