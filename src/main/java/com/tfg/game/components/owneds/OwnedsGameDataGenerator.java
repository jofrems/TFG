package com.tfg.game.components.owneds;

import com.tfg.game.ecs.EntityOwnDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.ecs.GameOwnDataGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OwnedsGameDataGenerator implements GameOwnDataGenerator {

    private final OwnedsRepository ownedsRepository;
    private final List<EntityOwnDataGenerator> entityOwnDataGenerators;
    private final OwnedsEntityDataGenerator ownedsEntityDataGenerator;

    public OwnedsGameDataGenerator(OwnedsRepository ownedsRepository, List<EntityOwnDataGenerator> entityOwnDataGenerators, OwnedsEntityDataGenerator ownedsEntityDataGenerator) {
        this.ownedsRepository = ownedsRepository;
        this.entityOwnDataGenerators = entityOwnDataGenerators;
        this.ownedsEntityDataGenerator = ownedsEntityDataGenerator;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer) {
        var playerOwneds = ownedsRepository.findAllByGameAndOwner(game, playingPlayer);
        var ownedEntityIds = playerOwneds.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityOwnDataGenerators.forEach(generator ->
            generator.generateOwnData(data, game, playingPlayer, ownedEntityIds)
        );
    }
}
