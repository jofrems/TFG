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

    public boolean ownTown(String entityId){
        var component = resourcesRepository.findById(entityId).get();
        if(component.getBrick() > 0 && component.getGrain() > 0 && component.getLumber() > 0 && component.getWool() > 0){
            component.incOrDecBrick(1,false);
            component.incOrDecGrain(1, false);
            component.incOrDecLumber(1, false);
            component.incOrDecWool(1, false);

            resourcesRepository.save(component);
            return true;
        }
        return false;
    }
}
