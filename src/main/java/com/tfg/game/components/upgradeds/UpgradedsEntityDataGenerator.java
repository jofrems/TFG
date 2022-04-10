package com.tfg.game.components.upgradeds;

import com.tfg.game.ecs.EntityOwnDataGenerator;
import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.EntityReachableDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpgradedsEntityDataGenerator implements EntityOwnDataGenerator, EntityPublicDataGenerator, EntityReachableDataGenerator {

    private final UpgradedsRepository upgradedsRepository;

    public UpgradedsEntityDataGenerator(UpgradedsRepository upgradedsRepository) {
        this.upgradedsRepository = upgradedsRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateTypedData(data, ownedEntityIds);
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> publicEntityIds) {
        generateTypedData(data, publicEntityIds);
    }

    @Override
    public void generateReachableData(GameData data, Game game, Player playingPlayer, List<String> reachableEntityIds) {
        generateTypedData(data, reachableEntityIds);
    }

    private void generateTypedData(GameData data, List<String> entityIds) {
        var components = upgradedsRepository.findAllById(entityIds);
        components.forEach(component -> {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "upgraded", component.isUpgraded());
        });
    }
}
