package com.tfg.game.components.upgradeds;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.ecs.GamePublicDataGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpgradedsGameDataGenerator implements GamePublicDataGenerator {

    private final UpgradedsRepository upgradedsRepository;
    private final List<EntityPublicDataGenerator> entityPublicDataGenerators;

    public UpgradedsGameDataGenerator(UpgradedsRepository upgradedsRepository, List<EntityPublicDataGenerator> entityPublicDataGenerators) {
        this.upgradedsRepository = upgradedsRepository;
        this.entityPublicDataGenerators = entityPublicDataGenerators;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer) {
        var components = upgradedsRepository.findAllByGame(game);
        var publicEntityIds = components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityPublicDataGenerators.forEach(g -> g.generatePublicData(data, game, playingPlayer, publicEntityIds));
    }
}
