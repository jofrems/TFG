package com.tfg.game.components.tileType;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.ecs.GamePublicDataGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TileTypeGameDataGenerator implements GamePublicDataGenerator {

    private final TileTypeRepository tileTypeRepository;
    private final List<EntityPublicDataGenerator> entityPublicDataGenerators;

    public TileTypeGameDataGenerator(TileTypeRepository tileTypeRepository, List<EntityPublicDataGenerator> entityPublicDataGenerators) {
        this.tileTypeRepository = tileTypeRepository;
        this.entityPublicDataGenerators = entityPublicDataGenerators;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer) {
        var components = tileTypeRepository.findAllByGame(game);
        var publicEntityIds = components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityPublicDataGenerators.forEach(g -> g.generatePublicData(data, game, playingPlayer, publicEntityIds));
    }
}
