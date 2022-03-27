package com.tfg.game.entities.road;

import com.tfg.game.components.locateds.LocatedsController;
import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.EntityIdGenerator;
import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class RoadFactory {
    private final EntityIdGenerator entityIdGenerator;
    private final TypedsController typedsController;
    private final OwnedsController ownedsController;
    private final LocatedsController positionController;

    //els roads hauran de tenir els vertex veins i els vertex hauran de tenir
    // els roads veins per poder comprovar si es pot construir o no un road
    //ex.: es vol contruir un road al costat d'un vertex. El road mirara els vertex veins si cap dels vertex no es
    // propietat d'aquell jugador o mirant els roads veins dels dos vertex veins no és cap propietat del jugador no
    // es podrà constuir.
    public RoadFactory(EntityIdGenerator entityIdGenerator, TypedsController typedsController, OwnedsController ownedsController, LocatedsController positionController) {
        this.entityIdGenerator = entityIdGenerator;
        this.typedsController = typedsController;
        this.ownedsController = ownedsController;
        this.positionController = positionController;
    }

    public void buildRoad(Game game, int x, int y) {
        var entityId = entityIdGenerator.nextEntityId("road");
        typedsController.create(entityId, game, "road");
        ownedsController.create(entityId, game);
        positionController.create(entityId, game, x, y);

    }

    /*public String getVertexIdGivenLocation(Game game, int x, int y){
        return positionController.findAllByGameAndXAndY(game, x, y).stream()
                .filter(c -> typedsController.isTyped(c.getEntityId(), "vertex"));
    }*/
}
