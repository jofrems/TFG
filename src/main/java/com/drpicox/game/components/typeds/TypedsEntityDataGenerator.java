package com.drpicox.game.components.typeds;

import com.drpicox.game.ecs.EntityOwnDataGenerator;
import com.drpicox.game.ecs.EntityPublicDataGenerator;
import com.drpicox.game.ecs.EntityReachableDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypedsEntityDataGenerator implements EntityOwnDataGenerator, EntityPublicDataGenerator, EntityReachableDataGenerator {

    private final TypedsRepository typedsRepository;

    public TypedsEntityDataGenerator(TypedsRepository typedsRepository) {
        this.typedsRepository = typedsRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateTypedData(data, ownedEntityIds);
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> publicEntityIds) {
        generateTypedData(data, publicEntityIds);
    }

    @Override
    public void generateReachableData(GameData data, Game game, Player playingPlayer, List<String> reachableEntityIds) {
        generateTypedData(data, reachableEntityIds);
    }

    private void generateTypedData(GameData data, List<String> entityIds) {
        var components = typedsRepository.findAllById(entityIds);
        components.forEach(component -> {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "isTyped", true);
            data.putEntityProperty(entityId, "type", component.getType());
        });
    }
}
