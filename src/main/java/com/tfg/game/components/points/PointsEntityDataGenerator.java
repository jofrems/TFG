package com.tfg.game.components.points;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointsEntityDataGenerator implements EntityPublicDataGenerator {
    private final PointsRepository pointsRepository;

    public PointsEntityDataGenerator(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generatePointsData(data, ownedEntityIds);
    }

    private void generatePointsData(GameData data, List<String> ownedEntityIds) {
        var components = pointsRepository.findAllById(ownedEntityIds);
        for (Points component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "points", component.getPoints());
        }
    }
}
