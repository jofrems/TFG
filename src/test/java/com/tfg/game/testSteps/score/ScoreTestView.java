package com.tfg.game.testSteps.score;

import com.tfg.game.testSteps.game.EntityResponse;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static com.tfg.game.testSteps.game.TypedTestView.byType;

@Component
public class ScoreTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;

    public ScoreTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }

    public String getScoreId(){
        return gameTestView.findEntity(byType("score")).get().getId();
    }

    public int getPoints(){
        var scoreId = getScoreId();
        var points = entityTestView.getEntityPropertyInt(scoreId, "points");

        return points;
    }
}
