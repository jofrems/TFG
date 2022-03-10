package com.tfg.game.entities.tile;

import com.tfg.game.components.locateds.LocatedsController;
import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.tileType.TileTypeController;
import com.tfg.game.components.tileWeight.TileWeightController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.EntityIdGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

@Component
public class TileFactory {

    private final EntityIdGenerator entityIdGenerator;
    private final TypedsController typedsController;
    private final OwnedsController ownedsController;
    private final TileTypeController tileTypeController;
    private final TileWeightController tileWeightController;
    private final LocatedsController positionController;


    public TileFactory(EntityIdGenerator entityIdGenerator, TypedsController typedsController, OwnedsController ownedsController, TileWeightController tileWeightController, TileTypeController tileTypeController, LocatedsController positionController) {
        this.entityIdGenerator = entityIdGenerator;
        this.typedsController = typedsController;
        this.ownedsController = ownedsController;
        this.tileWeightController = tileWeightController;
        this.tileTypeController = tileTypeController;
        this.positionController = positionController;
    }

    public void buildTile(Game game, Player owner, int tileType, int weight, int row, int column) {
        var entityId = entityIdGenerator.nextEntityId("tile");
        typedsController.create(entityId, game, "tile");
        ownedsController.create(entityId, game);
        tileTypeController.create(entityId,game, tileType);
        tileWeightController.create(entityId,game, weight);
        positionController.create(entityId, game, row, column);
    }
}
