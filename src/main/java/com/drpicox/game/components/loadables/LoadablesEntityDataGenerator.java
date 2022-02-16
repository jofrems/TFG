package com.drpicox.game.components.loadables;

import com.drpicox.game.ecs.EntityOwnDataGenerator;
import com.drpicox.game.ecs.GameData;
import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoadablesEntityDataGenerator implements EntityOwnDataGenerator {

    private final LoadablesRepository loadablesRepository;

    public LoadablesEntityDataGenerator(LoadablesRepository loadablesRepository) {
        this.loadablesRepository = loadablesRepository;
    }

    @Override
    public void generateOwnData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        var components = loadablesRepository.findAllById(ownedEntityIds);
        for (var component : components) {
            var entityId = component.getEntityId();
            var loadUnloadAmount = component.getLoadUnloadAmount();
            data.putEntityProperty(entityId, "isLoadable", true);
            data.putEntityProperty(entityId, "loadUnloadAmount", Math.abs(loadUnloadAmount));
            data.putEntityProperty(entityId, "loadRequested", loadUnloadAmount > 0);
            data.putEntityProperty(entityId, "unloadRequested", loadUnloadAmount < 0);
        }
    }
}
