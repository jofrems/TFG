package com.tfg.game.ecs;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;

import java.util.List;

public interface EntityPublicDataGenerator {
    void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> publicEntityIds);
}
