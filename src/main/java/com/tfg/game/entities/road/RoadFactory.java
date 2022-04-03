package com.tfg.game.entities.road;

import com.tfg.game.components.locateds.LocatedsController;
import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.partnerships.PartnershipController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.EntityIdGenerator;
import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class RoadFactory {
    private final EntityIdGenerator entityIdGenerator;
    private final TypedsController typedsController;
    private final OwnedsController ownedsController;
    private final LocatedsController locatedsController;
    private final PartnershipController partnershipController;

    //els roads hauran de tenir els vertex veins i els vertex hauran de tenir
    // els roads veins per poder comprovar si es pot construir o no un road
    //ex.: es vol contruir un road al costat d'un vertex. El road mirara els vertex veins si cap dels vertex no es
    // propietat d'aquell jugador o mirant els roads veins dels dos vertex veins no és cap propietat del jugador no
    // es podrà constuir.
    public RoadFactory(EntityIdGenerator entityIdGenerator, TypedsController typedsController, OwnedsController ownedsController, LocatedsController locatedsController, PartnershipController partnershipController) {
        this.entityIdGenerator = entityIdGenerator;
        this.typedsController = typedsController;
        this.ownedsController = ownedsController;
        this.locatedsController = locatedsController;
        this.partnershipController = partnershipController;
    }

    public void buildRoad(Game game, int x, int y, int adjVertex1LocX, int adjVertex1LocY, int adjVertex2LocX, int adjVertex2LocY) {
       // var test = getVertexIdGivenLocation(game, x, y);

        var entityId = entityIdGenerator.nextEntityId("road");
        typedsController.create(entityId, game, "road");
        ownedsController.create(entityId, game);
        locatedsController.create(entityId, game, x, y);
        partnershipController.create(entityId, game, getVertexIdGivenLocation(game, adjVertex1LocX, adjVertex1LocY), getVertexIdGivenLocation(game, adjVertex2LocX, adjVertex2LocY));
        partnershipController.update(getVertexIdGivenLocation(game, adjVertex1LocX, adjVertex1LocY), entityId);
        partnershipController.update(getVertexIdGivenLocation(game, adjVertex2LocX, adjVertex2LocY), entityId);
    }

    public String getVertexIdGivenLocation(Game game, int x, int y){
        return locatedsController.findAllByGameAndXAndY(game, x, y).stream()
                .filter(c -> typedsController.isTyped(c.getEntityId(), "vertex")).findFirst().get().getEntityId();
    }
}
