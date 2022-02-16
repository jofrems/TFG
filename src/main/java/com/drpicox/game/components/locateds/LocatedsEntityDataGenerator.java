package com.drpicox.game.components.locateds;

import com.drpicox.game.ecs.EntityOwnDataGenerator;
import com.drpicox.game.ecs.EntityPublicDataGenerator;
import com.drpicox.game.ecs.EntityReachableDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocatedsEntityDataGenerator implements EntityOwnDataGenerator, EntityPublicDataGenerator, EntityReachableDataGenerator {

    private final LocatedsRepository locatedsRepository;

    public LocatedsEntityDataGenerator(LocatedsRepository locatedsRepository) {
        this.locatedsRepository = locatedsRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateLocatedsData(data, ownedEntityIds);
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> publicEntityIds) {
        generateLocatedsData(data, publicEntityIds);
    }

    @Override
    public void generateReachableData(GameData data, Game game, Player playingPlayer, List<String> reachableEntityIds) {
        generateLocatedsData(data, reachableEntityIds);
    }

    private void generateLocatedsData(GameData data, List<String> entityIds) {
        var components = locatedsRepository.findAllById(entityIds);
        components.forEach(component -> {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "isLocated", true);
            data.putEntityProperty(entityId, "location", component.getLocation());
        });
    }
}
