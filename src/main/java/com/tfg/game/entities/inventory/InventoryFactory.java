package com.tfg.game.entities.inventory;

import com.tfg.game.components.resources.ResourcesController;
import com.tfg.game.components.elements.ElementsController;
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
    private final ElementsController elementsController;
    private final ResourcesController resourcesController;

    public InventoryFactory(EntityIdGenerator entityIdGenerator,  OwnedsController ownedsController, TypedsController typedsController, ElementsController elementsController, ResourcesController resourcesController) {
        this.entityIdGenerator = entityIdGenerator;
        this.ownedsController = ownedsController;
        this.typedsController = typedsController;
        this.elementsController = elementsController;
        this.resourcesController = resourcesController;
    }

    public void buildInventory(Game game, Player owner) {
        var entityId = entityIdGenerator.nextEntityId("inventory");
        ownedsController.create(entityId, game, owner);
        typedsController.create(entityId, game, "inventory");
        elementsController.create(entityId,game);
        resourcesController.create(entityId,game);
    }
}
