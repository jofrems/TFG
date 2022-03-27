package com.tfg.game.components.partnerships;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class PartnershipController {
    private final PartnershipRepository partnershipRepository;
    private final PartnershipEntityDataGenerator partnershipEntityDataGenerator;


    public PartnershipController(PartnershipRepository partnershipRepository, PartnershipEntityDataGenerator partnershipEntityDataGenerator) {
        this.partnershipRepository = partnershipRepository;
        this.partnershipEntityDataGenerator = partnershipEntityDataGenerator;
    }

    public void create(String entityId, Game game, String p1, String p2) {
        var component = new Partnership(entityId, game, p1, p2);
        partnershipRepository.save(component);
    }
}
