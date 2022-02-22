package com.tfg.game.components.owneds;

import com.tfg.game.ecs.EntityOwnDataGenerator;
import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.EntityReachableDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
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
            if(component.getOwner() != null) {
                data.putEntityProperty(entityId, "isOwned", true);
                data.putEntityProperty(entityId, "owner", component.getOwner().getPlayerName());
            }else{
                data.putEntityProperty(entityId, "isOwned", false);
            }
        });
    }
}
