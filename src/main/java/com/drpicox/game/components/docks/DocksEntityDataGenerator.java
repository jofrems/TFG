package com.drpicox.game.components.docks;

import com.drpicox.game.ecs.EntityOwnDataGenerator;
import com.drpicox.game.ecs.EntityReachableDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocksEntityDataGenerator implements EntityOwnDataGenerator, EntityReachableDataGenerator {

    private final DocksRepository docksRepository;

    public DocksEntityDataGenerator(DocksRepository docksRepository) {
        this.docksRepository = docksRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateDocksData(data, ownedEntityIds);
    }

    @Override
    public void generateReachableData(GameData data, Game game, Player playingPlayer, List<String> reachableEntityIds) {
        generateDocksData(data, reachableEntityIds);
    }

    private void generateDocksData(GameData data, List<String> entityIds) {
        var components = docksRepository.findAllById(entityIds);
        components.forEach(component -> {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "isDock", true);
        });
    }
}
