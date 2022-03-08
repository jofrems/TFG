package com.tfg.game.components.locateds;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocatedsEntityDataGenerator implements EntityPublicDataGenerator {

    private final LocatedsRepository locatedsRepository;

    public LocatedsEntityDataGenerator(LocatedsRepository locatedsRepository) {
        this.locatedsRepository = locatedsRepository;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> publicEntityIds) {
        generateLocatedData(data, publicEntityIds);
    }

    public void generateLocatedData(GameData data, List<String> entityIds) {
        var components = locatedsRepository.findAllById(entityIds);
        components.forEach(component -> {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "isLocated", true);
            data.putEntityProperty(entityId, "row", component.getRow());
            data.putEntityProperty(entityId, "column", component.getColumn());
        });
    }


}
