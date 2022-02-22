package com.tfg.game.entities.inventory;


import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.EntityIdGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

@Component
public class InventoryFactory {

    private final EntityIdGenerator entityIdGenerator;
    private final OwnedsController ownedsController;
    private final TypedsController typedsController;

    public InventoryFactory(EntityIdGenerator entityIdGenerator,  OwnedsController ownedsController, TypedsController typedsController) {
        this.entityIdGenerator = entityIdGenerator;
        this.ownedsController = ownedsController;
        this.typedsController = typedsController;
    }

    public void buildInventory(Game game, Player owner) {
        var entityId = entityIdGenerator.nextEntityId("inventory");
        ownedsController.create(entityId, game, owner);
        typedsController.create(entityId, game, "inventory");
    }
}
