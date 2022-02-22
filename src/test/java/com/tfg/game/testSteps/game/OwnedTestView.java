package com.tfg.game.testSteps.game;

import com.tfg.game.testSteps.game.EntityResponse;
import com.tfg.game.testSteps.game.EntityTestView;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class OwnedTestView {

    private final EntityTestView entityTestView;

    public OwnedTestView(EntityTestView entityTestView) {
        this.entityTestView = entityTestView;
    }

    public static Predicate<EntityResponse> byOwner(String ownerName) {
        return e -> e.getOrDefault("owner", "").equals(ownerName);
    }

    public String getOnwer() {
        return getOnwer(entityTestView.getEntityId());
    }

    public String getOnwer(String entityId) {
        return entityTestView.getEntityPropertyString(entityId, "owner");
    }
}
