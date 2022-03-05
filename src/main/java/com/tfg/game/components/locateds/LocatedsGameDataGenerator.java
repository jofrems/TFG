package com.tfg.game.components.locateds;

import com.tfg.game.ecs.GameData;
import com.tfg.game.players.Player;
import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GamePublicDataGenerator;
import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocatedsGameDataGenerator implements GamePublicDataGenerator {

    private final LocatedsRepository locatedsRepository;
    private final List<EntityPublicDataGenerator> entityPublicDataGenerators;

    public LocatedsGameDataGenerator(LocatedsRepository locatedsRepository, List<EntityPublicDataGenerator> entityPublicDataGenerators) {
        this.locatedsRepository = locatedsRepository;
        this.entityPublicDataGenerators = entityPublicDataGenerators;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer) {
        var components = locatedsRepository.findAllByGame(game);
        var publicEntityIds = components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityPublicDataGenerators.forEach(g -> g.generatePublicData(data, game, playingPlayer, publicEntityIds));
    }
}
