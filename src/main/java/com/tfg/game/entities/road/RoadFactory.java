package com.tfg.game.entities.road;

import com.tfg.game.components.locateds.Located;
import com.tfg.game.components.locateds.LocatedsController;
import com.tfg.game.components.owneds.OwnedsController;
import com.tfg.game.components.typeds.TypedsController;
import com.tfg.game.ecs.EntityIdGenerator;
import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class RoadFactory {
    private final EntityIdGenerator entityIdGenerator;
    private final TypedsController typedsController;
    private final OwnedsController ownedsController;
    private final LocatedsController locatedsController;

    //els roads hauran de tenir els vertex veins i els vertex hauran de tenir
    // els roads veins per poder comprovar si es pot construir o no un road
    //ex.: es vol contruir un road al costat d'un vertex. El road mirara els vertex veins si cap dels vertex no es
    // propietat d'aquell jugador o mirant els roads veins dels dos vertex veins no és cap propietat del jugador no
    // es podrà constuir.
    public RoadFactory(EntityIdGenerator entityIdGenerator, TypedsController typedsController, OwnedsController ownedsController, LocatedsController locatedsController) {
        this.entityIdGenerator = entityIdGenerator;
        this.typedsController = typedsController;
        this.ownedsController = ownedsController;
        this.locatedsController = locatedsController;
    }

    public void buildRoad(Game game, int x, int y) {
       // var test = getVertexIdGivenLocation(game, x, y);

        var entityId = entityIdGenerator.nextEntityId("road");
        typedsController.create(entityId, game, "road");
        ownedsController.create(entityId, game);
        locatedsController.create(entityId, game, x, y);
    }

    public String getVertexIdGivenLocation(Game game, int x, int y){
        return locatedsController.findAllByGameAndXAndY(game, x, y).stream()
                .filter(c -> typedsController.isTyped(c.getEntityId(), "vertex")).findFirst().get().getEntityId();
    }
}
