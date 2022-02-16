package com.drpicox.game.entities.ships;

import com.drpicox.game.components.locateds.LocatedsController;
import com.drpicox.game.components.sails.SailsController;
import com.drpicox.game.ecs.EntityIdGenerator;
import com.drpicox.game.games.Game;
import com.drpicox.game.components.loadables.LoadablesController;
import com.drpicox.game.components.nameds.NamedsController;
import com.drpicox.game.components.owneds.OwnedsController;
import com.drpicox.game.players.Player;
import com.drpicox.game.components.populateds.PopulatedsController;
import com.drpicox.game.components.typeds.TypedsController;
import org.springframework.stereotype.Component;

@Component
public class ShipFactory {

    private final NamedsController namedsController;
    private final OwnedsController ownedsController;
    private final PopulatedsController populatedsController;
    private final TypedsController typedsController;
    private final LoadablesController loadablesController;
    private final EntityIdGenerator entityIdGenerator;
    private final LocatedsController locatedsController;
    private final SailsController sailsController;

    public ShipFactory(NamedsController namedsController, OwnedsController ownedsController, PopulatedsController populatedsController, TypedsController typedsController, LoadablesController loadablesController, EntityIdGenerator entityIdGenerator, LocatedsController locatedsController, SailsController sailsController) {
        this.namedsController = namedsController;
        this.ownedsController = ownedsController;
        this.populatedsController = populatedsController;
        this.typedsController = typedsController;
        this.loadablesController = loadablesController;
        this.entityIdGenerator = entityIdGenerator;
        this.locatedsController = locatedsController;
        this.sailsController = sailsController;
    }

    public void buildShip(Game game, Player owner, String dockId, String shipName, int initialLocation) {
        var entityId = entityIdGenerator.nextEntityId("vaixell");

        namedsController.create(entityId, game, shipName);
        ownedsController.create(entityId, game, owner);
        populatedsController.create(entityId, game, 0);
        typedsController.create(entityId, game, "ship");
        loadablesController.create(entityId, game);
        locatedsController.create(entityId, game, initialLocation);
        sailsController.create(entityId, game);
    }

}
