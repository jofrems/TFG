package com.drpicox.game.components.typeds;

import com.drpicox.game.ecs.EntityPublicDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.ecs.GamePublicDataGenerator;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
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
        var components = typedsRepository.findAllByType("city");
        var publicEntityIds = components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityPublicDataGenerators.forEach(g -> g.generatePublicData(data, game, playingPlayer, publicEntityIds));
    }
}
