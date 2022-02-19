package com.tfg.game.ecs;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;

import java.util.List;

public interface EntityReachableDataGenerator {
    void generateReachableData(GameData data, Game game, Player playingPlayer, List<String> reachableEntityIds);
}
