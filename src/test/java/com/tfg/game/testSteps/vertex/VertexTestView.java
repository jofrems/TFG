package com.tfg.game.testSteps.vertex;

import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import static com.tfg.game.testSteps.game.TypedTestView.byType;

@Component
public class VertexTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;

    public VertexTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }
    public int countVertex(){return gameTestView.countEntities(byType("vertex"));}

}
