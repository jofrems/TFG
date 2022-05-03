package com.tfg.game.components.dices;

import com.tfg.game.ecs.EntityOwnDataGenerator;
import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DicesEntityDataGenerator implements EntityPublicDataGenerator {
    private final DicesRepository dicesRepository;

    public DicesEntityDataGenerator(DicesRepository dicesRepository) {
        this.dicesRepository = dicesRepository;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> ownedEntityIds) {
        generateDicesData(data, ownedEntityIds);
    }

    private void generateDicesData(GameData data, List<String> dicesEntityIds) {
        var components = dicesRepository.findAllById(dicesEntityIds);
        for (Dices component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "dice1", component.getDice1());
            data.putEntityProperty(entityId, "dice2", component.getDice2());
            data.putEntityProperty(entityId, "isRolled", component.isRolled());

        }
    }
}
