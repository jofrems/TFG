package com.tfg.game.components.elements;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElementsController {
    private final ElementsRepository elementsRepository;
    private final ElementsEntityDataGenerator elementsEntityDataGenerator;


    public ElementsController(ElementsRepository elementsRepository, ElementsEntityDataGenerator elementsEntityDataGenerator) {
        this.elementsRepository = elementsRepository;
        this.elementsEntityDataGenerator = elementsEntityDataGenerator;
    }

    public void create(String entityId, Game game) {
        var component = new Elements(entityId, game);
        elementsRepository.save(component);
    }

    public boolean ownTown(String entityId, boolean shouldOwnTown){
        var component = elementsRepository.findById(entityId).get();
        if(!shouldOwnTown || component.getTowns() <= 0) return false;

        component.decreaseTowns();
        elementsRepository.save(component);

        return true;
    }

    public boolean ownCity(String entityId, boolean shouldOwnCity){
        var component = elementsRepository.findById(entityId).get();
        if(!shouldOwnCity || component.getCities() <= 0) return false;

        component.decreaseCities();
        component.increaseTowns();
        elementsRepository.save(component);

        return true;
    }

    public boolean ownRoad(String entityId, boolean shouldOwnRoad){
        var component = elementsRepository.findById(entityId).get();
        if(!shouldOwnRoad || component.getRoads() <= 0) return false;

        component.decreaseRoads();
        elementsRepository.save(component);

        return true;
    }
}
