package com.tfg.game.testSteps.vertex;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import com.tfg.game.testSteps.tiles.TilesTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class TheShouldBeVertexStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final VertexTestView vertexTestView;
    private final GameTestView gameTestView;

    public TheShouldBeVertexStep(EntityTestView entityTestView, VertexTestView vertexTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
        this.vertexTestView = vertexTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be (\\d+) vertex";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        if (match == null) return;
        var expectedValue = Integer.parseInt(match[1]);
        var value = vertexTestView.countVertex();

        assertThat(value).isEqualTo(expectedValue);
    }

}
