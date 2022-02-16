package com.drpicox.game.ecs;

import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;

import java.util.List;

public interface EntityPublicDataGenerator {
    void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> publicEntityIds);
}
