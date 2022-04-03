package com.tfg.game.testSteps.partnerships;

import com.tfg.game.testSteps.game.EntityResponse;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static com.tfg.game.testSteps.game.TypedTestView.byType;

@Component
public class PartnershipTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;


    public PartnershipTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }

    public static Predicate<EntityResponse> byOwner(String ownerName) {
        return e -> e.getOrDefault("owner", "").equals(ownerName);
    }


    public String getRoadId(){
        return gameTestView.findEntity(byType("road")).get().getId();
    }


    public int getCountPartnerships(String entityId){
        var p1 = entityTestView.getEntityPropertyString(entityId, "partnership1");
        var p2 = entityTestView.getEntityPropertyString(entityId, "partnership2");
        var counter = 0;

        if(p1 != null)
            counter++;
        if(p2 != null)
            counter++;

        return counter;
    }
}
