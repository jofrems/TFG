package com.tfg.game.testSteps.tiles;

import com.tfg.game.testSteps.game.EntityResponse;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

import static com.tfg.game.testSteps.game.TypedTestView.byType;

@Component
public class TilesTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;

    public TilesTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }
    public String getTilesId(){
        return gameTestView.findEntity(byType("tile")).get().getId();
    }
    public int countTiles(){return gameTestView.countEntities(byType("tile"));}

    //public Optional<EntityResponse> getEntitiesWithProperty(){return gameTestView.getEntitiesWithProperty(byType("tile"),byTileType(-1));}

    public static Predicate<EntityResponse> byTileType(int tileType) {
        return e -> e.getOrDefault("TileType", "").equals(tileType);
    }

    public String getRoads(String entityId) {
        return entityTestView.getEntityPropertyString(entityId, "roads");
    }
    public String getTowns(String entityId) {
        return entityTestView.getEntityPropertyString(entityId, "towns");
    }
    public String getCities(String entityId) {
        return entityTestView.getEntityPropertyString(entityId, "cities");
    }
}
