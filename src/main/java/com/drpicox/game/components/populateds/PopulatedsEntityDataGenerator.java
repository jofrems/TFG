package com.drpicox.game.components.populateds;

import com.drpicox.game.components.docks.DocksController;
import com.drpicox.game.ecs.EntityOwnDataGenerator;
import com.drpicox.game.ecs.EntityReachableDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PopulatedsEntityDataGenerator implements EntityOwnDataGenerator, EntityReachableDataGenerator {

    private final PopulatedsRepository populatedsRepository;
    private final DocksController docksController;

    public PopulatedsEntityDataGenerator(PopulatedsRepository populatedsRepository, DocksController docksController) {
        this.populatedsRepository = populatedsRepository;
        this.docksController = docksController;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generatePopulatedData(data, ownedEntityIds);
    }

    @Override
    public void generateReachableData(GameData data, Game game, Player playingPlayer, List<String> reachableEntityIds) {
        var dockIds = docksController.findAllById(reachableEntityIds).stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        generatePopulatedData(data, dockIds);
    }

    private void generatePopulatedData(GameData data, List<String> ownedEntityIds) {
        var components = populatedsRepository.findAllById(ownedEntityIds);
        for (Populated component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "isPopulated", true);
            data.putEntityProperty(entityId, "population", component.getPopulation());
        }
    }
}
