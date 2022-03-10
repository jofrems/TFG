package com.tfg.game.components.tileType;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TileTypeEntityDataGenerator implements EntityPublicDataGenerator {
    private final TileTypeRepository tileWeightRepository;

    public TileTypeEntityDataGenerator(TileTypeRepository tileWeightRepository) {
        this.tileWeightRepository = tileWeightRepository;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateTileWeightData(data, ownedEntityIds);
    }

    private void generateTileWeightData(GameData data, List<String> ownedEntityIds) {
        var components = tileWeightRepository.findAllById(ownedEntityIds);
        for (TileType component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "TileType", component.getType());
        }
    }
}
