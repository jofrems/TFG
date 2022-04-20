package com.tfg.game.entities.dices;

import com.tfg.game.components.dices.DicesController;
import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.EntityIdGenerator;
import com.tfg.game.games.Game;

import org.springframework.stereotype.Component;

@Component
public class DicesFactory {
    private final EntityIdGenerator entityIdGenerator;
    private final TypedsController typedsController;
    private final DicesController dicesController;
    private final OwnedsController ownedsController;

    public DicesFactory(EntityIdGenerator entityIdGenerator, TypedsController typedsController, DicesController dicesController, OwnedsController ownedsController) {
        this.entityIdGenerator = entityIdGenerator;
        this.typedsController = typedsController;
        this.dicesController = dicesController;
        this.ownedsController = ownedsController;
    }

    public void buildDices(Game game) {
        var entityId = entityIdGenerator.nextEntityId("dice");
        typedsController.create(entityId, game, "dice");
        dicesController.create(entityId, game);
        ownedsController.create(entityId, game);
    }
}
