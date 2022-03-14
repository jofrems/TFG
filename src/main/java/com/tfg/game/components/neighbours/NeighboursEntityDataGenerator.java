package com.tfg.game.components.neighbours;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NeighboursEntityDataGenerator implements EntityPublicDataGenerator {
    private final NeighboursRepository neighboursRepository;

    public NeighboursEntityDataGenerator(NeighboursRepository neighboursRepository) {
        this.neighboursRepository = neighboursRepository;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateNeighboursData(data, ownedEntityIds);
    }

    private void generateNeighboursData(GameData data, List<String> ownedEntityIds) {
        var components = neighboursRepository.findAllById(ownedEntityIds);
        for (Neighbours component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "nWeight", component.getNeighbourWeight());
            data.putEntityProperty(entityId, "nType", component.getNeighbourType());

            data.putEntityProperty(entityId, "nWeight2", component.getNeighbour2Weight());
            data.putEntityProperty(entityId, "nType2", component.getNeighbour2Type());

            data.putEntityProperty(entityId, "nWeight3", component.getNeighbour3Weight());
            data.putEntityProperty(entityId, "nType3", component.getNeighbour3Type());


        }
    }
}
