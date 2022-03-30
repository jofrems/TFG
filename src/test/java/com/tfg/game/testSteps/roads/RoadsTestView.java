package com.tfg.game.testSteps.roads;

import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import static com.tfg.game.testSteps.game.TypedTestView.byType;

@Component
public class RoadsTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;

    public RoadsTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }
    public int countRoads(){return gameTestView.countEntities(byType("road"));}

}
