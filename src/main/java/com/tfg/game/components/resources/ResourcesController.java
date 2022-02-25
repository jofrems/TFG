package com.tfg.game.components.resources;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class ResourcesController {
    private final ResourcesRepository resourcesRepository;
    private final ResourcesEntityDataGenerator resourcesEntityDataGenerator;


    public ResourcesController(ResourcesRepository resourcesRepository, ResourcesEntityDataGenerator resourcesEntityDataGenerator) {
        this.resourcesRepository = resourcesRepository;
        this.resourcesEntityDataGenerator = resourcesEntityDataGenerator;
    }

    public void create(String entityId, Game game) {
        var component = new Resources(entityId, game);
        resourcesRepository.save(component);
    }
}
