package com.drpicox.game.components.sails;

import com.drpicox.game.ecs.EntityOwnDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SailsEntityDataGenerator implements EntityOwnDataGenerator {

    private final SailsRepository sailsRepository;

    public SailsEntityDataGenerator(SailsRepository sailsRepository) {
        this.sailsRepository = sailsRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        var components = sailsRepository.findAllById(ownedEntityIds);
        components.forEach(component -> {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "isSail", true);
            data.putEntityProperty(entityId, "destinationSail", component.isDestinationSail());
            data.putEntityProperty(entityId, "destinationLocation", component.getDestinationLocation());
        });
    }
}
