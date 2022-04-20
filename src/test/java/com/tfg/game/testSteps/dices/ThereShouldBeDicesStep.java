package com.tfg.game.testSteps.dices;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import com.tfg.game.testSteps.roads.RoadsTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ThereShouldBeDicesStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final DicesTestView dicesTestView;
    private final GameTestView gameTestView;

    public ThereShouldBeDicesStep(EntityTestView entityTestView, DicesTestView dicesTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
        this.dicesTestView = dicesTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be dices";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        if (match == null) return;
        var value = dicesTestView.countDices();

        assertThat(value).isGreaterThan(0);
    }

}
