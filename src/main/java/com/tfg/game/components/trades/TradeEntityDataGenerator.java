package com.tfg.game.components.trades;

import com.tfg.game.ecs.EntityOwnDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TradeEntityDataGenerator implements EntityOwnDataGenerator{
    private final TradeRepository tradeRepository;

    public TradeEntityDataGenerator(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateResourcesData(data, ownedEntityIds);
    }

    private void generateResourcesData(GameData data, List<String> ownedEntityIds) {
        var components = tradeRepository.findAllById(ownedEntityIds);
        for (Trade component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "givenResources", component.getGivenResources());
            data.putEntityProperty(entityId, "returnedResources", component.getReturnedResources());
        }
    }
}
