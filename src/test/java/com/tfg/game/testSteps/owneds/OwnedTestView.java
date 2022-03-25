package com.tfg.game.testSteps.owneds;

import com.tfg.game.testSteps.game.EntityResponse;
import com.tfg.game.testSteps.game.EntityTestView;
import org.springframework.stereotype.Component;
import com.tfg.game.testSteps.game.GameTestView;

import java.util.function.Predicate;

import static com.tfg.game.testSteps.game.TypedTestView.byType;

@Component
public class OwnedTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;


    public OwnedTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }

    public static Predicate<EntityResponse> byOwner(String ownerName) {
        return e -> e.getOrDefault("owner", "").equals(ownerName);
    }

    public String getOwner() {
        return getOwner(entityTestView.getEntityId());
    }

    public String getVertexId(){
        return gameTestView.findEntity(byType("vertex")).get().getId();
    }

    public String getOwner(String entityId) {
        return entityTestView.getEntityPropertyString(entityId, "owner");
    }

    public Boolean getHasOwner(String entityId){return entityTestView.getEntityPropertyBoolean(entityId, "isOwned");}
}
