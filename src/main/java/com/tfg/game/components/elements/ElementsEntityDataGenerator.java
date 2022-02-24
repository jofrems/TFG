package com.tfg.game.components.elements;

import com.tfg.game.ecs.EntityOwnDataGenerator;
import com.tfg.game.ecs.EntityReachableDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElementsEntityDataGenerator implements EntityOwnDataGenerator{
    private final ElementsRepository elementsRepository;

    public ElementsEntityDataGenerator(ElementsRepository elementsRepository) {
        this.elementsRepository = elementsRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateElementsData(data, ownedEntityIds);
    }

    private void generateElementsData(GameData data, List<String> ownedEntityIds) {
        var components = elementsRepository.findAllById(ownedEntityIds);
        for (Elements component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "haveElements", true);
            data.putEntityProperty(entityId, "roads", component.getRoads());
            data.putEntityProperty(entityId, "towns", component.getTowns());
            data.putEntityProperty(entityId, "cities", component.getCities());
        }
    }
}
