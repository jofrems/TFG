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

    public boolean ownTown(String entityId, boolean shouldOwnTown){
        if(!shouldOwnTown) return false;

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

    public boolean ownCity(String entityId, boolean shouldOwnCity){
        if(!shouldOwnCity) return false;
        var component = resourcesRepository.findById(entityId).get();
        if(component.getGrain() > 1 && component.getOre() > 2){
            component.incOrDecOre(1,false);
            component.incOrDecGrain(2, false);

            resourcesRepository.save(component);
            return true;
        }
        return false;
    }

    public boolean ownRoad(String entityId, boolean shouldOwnRoad){
        if(!shouldOwnRoad) return false;
        var component = resourcesRepository.findById(entityId).get();
        if(component.getBrick() > 0 && component.getLumber() > 0){
            component.incOrDecBrick(1,false);
            component.incOrDecLumber(1, false);

            resourcesRepository.save(component);
            return true;
        }
        return false;
    }

    public void incOrDecBrick (String entityId, int amount, boolean increase) {
        var component = resourcesRepository.findById(entityId).get();
        component.incOrDecBrick(amount, increase);

        resourcesRepository.save(component);
    }

    public void incOrDecLumber (String entityId, int amount, boolean increase) {
        var component = resourcesRepository.findById(entityId).get();
        component.incOrDecLumber(amount, increase);

        resourcesRepository.save(component);
    }

    public void incOrDecWool (String entityId, int amount, boolean increase) {
        var component = resourcesRepository.findById(entityId).get();
        component.incOrDecWool(amount, increase);

        resourcesRepository.save(component);
    }

    public void incOrDecGrain (String entityId, int amount, boolean increase) {
        var component = resourcesRepository.findById(entityId).get();
        component.incOrDecGrain(amount, increase);

        resourcesRepository.save(component);
    }

    public void incOrDecOre (String entityId, int amount, boolean increase) {
        var component = resourcesRepository.findById(entityId).get();
        component.incOrDecOre(amount, increase);

        resourcesRepository.save(component);
    }

}
