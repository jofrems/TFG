package com.tfg.game.components.typeds;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.ecs.GamePublicDataGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypedsGameDataGenerator implements GamePublicDataGenerator {

    private final TypedsRepository typedsRepository;
    private final List<EntityPublicDataGenerator> entityPublicDataGenerators;

    public TypedsGameDataGenerator(TypedsRepository typedsRepository, List<EntityPublicDataGenerator> entityPublicDataGenerators) {
        this.typedsRepository = typedsRepository;
        this.entityPublicDataGenerators = entityPublicDataGenerators;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer) {
        var components = typedsRepository.findAllByType("inventory");
        var publicEntityIds = components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityPublicDataGenerators.forEach(g -> g.generatePublicData(data, game, playingPlayer, publicEntityIds));
    }
}
