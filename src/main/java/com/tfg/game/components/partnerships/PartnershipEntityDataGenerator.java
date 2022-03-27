package com.tfg.game.components.partnerships;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PartnershipEntityDataGenerator implements EntityPublicDataGenerator {
    private final PartnershipRepository partnershipRepository;

    public PartnershipEntityDataGenerator(PartnershipRepository partnershipRepository) {
        this.partnershipRepository = partnershipRepository;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer, List<String> partnershipEntityIds) {
        generatePartnershipData(data, partnershipEntityIds);
    }

    private void generatePartnershipData(GameData data, List<String> partnershipEntityIds) {
        var components = partnershipRepository.findAllById(partnershipEntityIds);
        for (Partnership component : components) {
            var entityId = component.getEntityId();
            data.putEntityProperty(entityId, "partnership1", component.getPartnership1());
            data.putEntityProperty(entityId, "partnership2", component.getPartnership2());
        }
    }
}
