package com.drpicox.game.components.owneds;

import com.drpicox.game.ecs.EntityOwnDataGenerator;
import com.drpicox.game.ecs.EntityPublicDataGenerator;
import com.drpicox.game.ecs.EntityReachableDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnedsEntityDataGenerator implements EntityOwnDataGenerator, EntityPublicDataGenerator, EntityReachableDataGenerator {

    private final OwnedsRepository ownedsRepository;

    public OwnedsEntityDataGenerator(OwnedsRepository ownedsRepository) {
        this.ownedsRepository = ownedsRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateOwnedData(data, ownedEntityIds);
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> publicEntityIds) {
        generateOwnedData(data, publicEntityIds);
    }

    @Override
    public void generateReachableData(GameData data, Game game, Player playingPlayer, List<String> reachableEntityIds) {
        generateOwnedData(data, reachableEntityIds);
    }

    private void generateOwnedData(GameData data, List<String> entityIds) {
        var playerOwneds = ownedsRepository.findAllById(entityIds);
        playerOwneds.forEach(component -> {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "isOwned", true);
            data.putEntityProperty(entityId, "owner", component.getOwner().getPlayerName());
        });
    }
}
