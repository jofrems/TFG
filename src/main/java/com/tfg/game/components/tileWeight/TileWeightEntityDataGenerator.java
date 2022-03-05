package com.tfg.game.components.tileWeight;

import com.tfg.game.components.elements.Elements;
import com.tfg.game.ecs.EntityOwnDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TileWeightEntityDataGenerator implements EntityOwnDataGenerator{
    private final TileWeightRepository tileWeightRepository;

    public TileWeightEntityDataGenerator(TileWeightRepository tileWeightRepository) {
        this.tileWeightRepository = tileWeightRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateTileWeightData(data, ownedEntityIds);
    }

    private void generateTileWeightData(GameData data, List<String> ownedEntityIds) {
        var components = tileWeightRepository.findAllById(ownedEntityIds);
        for (TileWeight component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "TileWeight", component.getWeight());
        }
    }
}
