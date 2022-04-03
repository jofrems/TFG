package com.tfg.game.entities.vertex;

import com.tfg.game.components.neighbours.NeighboursController;
import com.tfg.game.components.locateds.LocatedsController;
import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.partnerships.PartnershipController;
import com.tfg.game.components.tileType.TileTypeController;
import com.tfg.game.components.tileWeight.TileWeightController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.EntityIdGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

@Component
public class VertexFactory {

    private final EntityIdGenerator entityIdGenerator;
    private final TypedsController typedsController;
    private final OwnedsController ownedsController;
    private final TileTypeController tileTypeController;
    private final TileWeightController tileWeightController;
    private final LocatedsController positionController;
    private final NeighboursController neighboursController;
    private final PartnershipController partnershipController;


    public VertexFactory(EntityIdGenerator entityIdGenerator, TypedsController typedsController, OwnedsController ownedsController, TileWeightController tileWeightController, TileTypeController tileTypeController, LocatedsController positionController, NeighboursController neighboursController, PartnershipController partnershipController) {
        this.entityIdGenerator = entityIdGenerator;
        this.typedsController = typedsController;
        this.ownedsController = ownedsController;
        this.tileWeightController = tileWeightController;
        this.tileTypeController = tileTypeController;
        this.positionController = positionController;
        this.neighboursController = neighboursController;
        this.partnershipController = partnershipController;
    }

    public void buildVertex(Game game, Player owner, int x, int y, int nWeight, int nType, int nWeight2, int nType2,int nWeight3,int nType3) {
        var entityId = entityIdGenerator.nextEntityId("vertex");
        typedsController.create(entityId, game, "vertex");
        ownedsController.create(entityId, game);
        positionController.create(entityId, game, x, y);
        neighboursController.create(entityId, game, nWeight,nType, nWeight2,nType2, nWeight3, nType3);
        partnershipController.createEmpty(entityId, game);
    }
}
