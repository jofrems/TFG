package com.tfg.game.entities.score;

import com.tfg.game.components.elements.ElementsController;
import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.points.PointsController;
import com.tfg.game.components.resources.ResourcesController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.EntityIdGenerator;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

@Component
public class ScoreFactory {

    private final EntityIdGenerator entityIdGenerator;
    private final OwnedsController ownedsController;
    private final TypedsController typedsController;
    private final PointsController pointsController;

    public ScoreFactory(EntityIdGenerator entityIdGenerator, OwnedsController ownedsController, TypedsController typedsController, PointsController pointsController) {
        this.entityIdGenerator = entityIdGenerator;
        this.ownedsController = ownedsController;
        this.typedsController = typedsController;
        this.pointsController = pointsController;
    }

    public void buildInventory(Game game, Player owner) {
        var entityId = entityIdGenerator.nextEntityId("score");
        ownedsController.create(entityId, game, owner);
        typedsController.create(entityId, game, "score");
        pointsController.create(entityId, game, 0);
    }
}
