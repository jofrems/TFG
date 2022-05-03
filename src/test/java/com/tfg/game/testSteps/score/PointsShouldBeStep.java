package com.tfg.game.testSteps.score;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import com.tfg.game.testSteps.tiles.TilesTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class PointsShouldBeStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final ScoreTestView scoreTestView;
    private final GameTestView gameTestView;

    public PointsShouldBeStep(EntityTestView entityTestView, ScoreTestView scoreTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.scoreTestView = scoreTestView;
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "Points should be (\\d+)";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        if (match == null) return;
        var expectedValue = Integer.parseInt(match[1]);
        var value = scoreTestView.getPoints();

        assertThat(value).isEqualTo(expectedValue);
    }

}
