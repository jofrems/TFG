package com.tfg.game.components.resources;

import com.tfg.game.ecs.EntityOwnDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourcesEntityDataGenerator implements EntityOwnDataGenerator{
    private final ResourcesRepository resourcesRepository;

    public ResourcesEntityDataGenerator (ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateResourcesData(data, ownedEntityIds);
    }

    private void generateResourcesData(GameData data, List<String> ownedEntityIds) {
        var components = resourcesRepository.findAllById(ownedEntityIds);
        for (Resources component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "haveResources", true);
            data.putEntityProperty(entityId, "brick", component.getBrick());
            data.putEntityProperty(entityId, "lumber", component.getLumber());
            data.putEntityProperty(entityId, "wool", component.getWool());
            data.putEntityProperty(entityId, "grain", component.getGrain());
            data.putEntityProperty(entityId, "ore", component.getOre());
        }
    }
}
