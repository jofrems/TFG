package com.tfg.game.components.dices;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.ecs.GamePublicDataGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DicesGameDataGenerator implements GamePublicDataGenerator {

    private final DicesRepository dicesRepository;
    private final List<EntityPublicDataGenerator> entityPublicDataGenerators;

    public DicesGameDataGenerator(DicesRepository dicesRepository, List<EntityPublicDataGenerator> entityPublicDataGenerators) {
        this.dicesRepository = dicesRepository;
        this.entityPublicDataGenerators = entityPublicDataGenerators;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer) {
        var components = dicesRepository.findAllByGame(game);
        var publicEntityIds = components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityPublicDataGenerators.forEach(g -> g.generatePublicData(data, game, playingPlayer, publicEntityIds));
    }
}
