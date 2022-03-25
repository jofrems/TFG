package com.tfg.game.testSteps.inventory;

import com.tfg.game.testSteps.game.EntityResponse;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;


import static com.tfg.game.testSteps.game.TypedTestView.byType;

@Component
public class InventoryTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;

    public InventoryTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }
    public String getInventoryId(){
        return gameTestView.findEntity(byType("inventory")).get().getId();
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
