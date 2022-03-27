package com.tfg.game.components.partnerships;

import com.tfg.game.ecs.EntityPublicDataGenerator;
import com.tfg.game.ecs.GameData;
import com.tfg.game.ecs.GamePublicDataGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PartnershipGameDataGenerator implements GamePublicDataGenerator {

    private final PartnershipRepository partnershipRepository;
    private final List<EntityPublicDataGenerator> entityPublicDataGenerators;

    public PartnershipGameDataGenerator(PartnershipRepository partnershipRepository, List<EntityPublicDataGenerator> entityPublicDataGenerators) {
        this.partnershipRepository = partnershipRepository;
        this.entityPublicDataGenerators = entityPublicDataGenerators;
    }

    @Override
    public void generatePublicData(GameData data, Game game, Player playingPlayer) {
        var components = partnershipRepository.findAllByGame(game);
        var publicEntityIds = components.stream().map(c -> c.getEntityId()).collect(Collectors.toList());
        entityPublicDataGenerators.forEach(g -> g.generatePublicData(data, game, playingPlayer, publicEntityIds));
    }
}
