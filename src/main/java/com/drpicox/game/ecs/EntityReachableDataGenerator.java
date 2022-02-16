package com.drpicox.game.ecs;

import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;

import java.util.List;

public interface EntityReachableDataGenerator {
    void generateReachableData(GameData data, Game game, Player playingPlayer, List<String> reachableEntityIds);
}
