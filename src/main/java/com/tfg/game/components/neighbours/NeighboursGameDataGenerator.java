package com.tfg.game.components.neighbours;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.ecs.GamePublicDataGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NeighboursGameDataGenerator implements GamePublicDataGenerator {

    private final NeighboursRepository neighboursRepository;
    private final List<EntityPublicDataGenerator> entityPublicDataGenerators;

    public NeighboursGameDataGenerator(NeighboursRepository neighboursRepository, List<EntityPublicDataGenerator> entityPublicDataGenerators) {
        this.neighboursRepository = neighboursRepository;
        this.entityPublicDataGenerators = entityPublicDataGenerators;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer) {
        var components = neighboursRepository.findAllByGame(game);
        var publicEntityIds = components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityPublicDataGenerators.forEach(g -> g.generatePublicData(data, game, playingPlayer, publicEntityIds));
    }
}
