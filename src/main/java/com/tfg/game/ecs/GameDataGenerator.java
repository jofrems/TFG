package com.tfg.game.ecs;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameDataGenerator {

    private final List<GameOwnDataGenerator> gameOwnDataGenerators;
    private final List<GamePublicDataGenerator> gamePublicDataGenerators;
    private final List<GameReachableDataGenerator> gameReachableDataGenerators;

    public GameDataGenerator(List<GameOwnDataGenerator> gameOwnDataGenerators, List<GamePublicDataGenerator> gamePublicDataGenerators, List<GameReachableDataGenerator> gameReachableDataGenerators) {
        this.gameOwnDataGenerators = gameOwnDataGenerators;
        this.gamePublicDataGenerators = gamePublicDataGenerators;
        this.gameReachableDataGenerators = gameReachableDataGenerators;
    }

    public GameData generate(Game game, Player playingPlayer, String token) {
        var data = new GameData(game, playingPlayer.getPlayerName(), token);

        gameOwnDataGenerators.stream().forEach(r -> r.generateOwnData(data, game, playingPlayer));
        gamePublicDataGenerators.stream().forEach(r -> r.generatePublicData(data, game, playingPlayer));
        gameReachableDataGenerators.stream().forEach(r -> r.generateReachableData(data, game, playingPlayer));

        return data;
    }
}
