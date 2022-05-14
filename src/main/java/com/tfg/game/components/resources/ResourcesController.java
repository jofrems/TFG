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

    public boolean makeTrade(String entityId, String givenResources, String getResources){
        var component = resourcesRepository.findById(entityId).get();

        var lumbers = component.getLumber();
        if(givenResources.equals("lumber") && component.getLumber() >= 4){
            incOrDecLumber(entityId, 4, false);
        }else if(givenResources.equals("wool") && component.getWool() >= 4){
            incOrDecWool(entityId, 4, false);
        }else if(givenResources.equals("brick") && component.getBrick() >= 4){
            incOrDecBrick(entityId, 4, false);
        }else if(givenResources.equals("grain") && component.getGrain() >= 4){
            incOrDecGrain(entityId, 4, false);
        }else if(givenResources.equals("ore") && component.getOre() >= 4){
            incOrDecOre(entityId, 4, false);
        }else{
            return false;
        }
        if(getResources.equals("lumber")){
            incOrDecLumber(entityId, 1, true);
        }else if(getResources.equals("wool")){
            incOrDecWool(entityId, 1, true);
        }else if(getResources.equals("brick")){
            incOrDecBrick(entityId, 1, true);
        }else if(getResources.equals("grain")){
            incOrDecGrain(entityId, 1, true);
        }else if(getResources.equals("ore")){
            incOrDecOre(entityId, 1, true);
        }

        return true;
    }
    public Game getGame(String entityId){
        var component = resourcesRepository.findById(entityId).get();

        return component.getGame();
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
