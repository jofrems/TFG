package com.tfg.game.testSteps.roads;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class TheMapShouldContainRoadsStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final RoadsTestView roadsTestView;
    private final GameTestView gameTestView;

    public TheMapShouldContainRoadsStep(EntityTestView entityTestView, RoadsTestView roadsTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
        this.roadsTestView = roadsTestView;
    }

    @Override
    protected String getRegex() {
        return "The map should contain (\\d+) roads";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        if (match == null) return;
        var expectedValue = Integer.parseInt(match[1]);
        var value = roadsTestView.countRoads();

        assertThat(value).isEqualTo(expectedValue);
    }

}
