package com.tfg.game.testSteps.dices;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import com.tfg.game.testSteps.game.PrettyKey;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ShouldNotBeStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final DicesTestView dicesTestView;
    private final GameTestView gameTestView;

    public ShouldNotBeStep(EntityTestView entityTestView, DicesTestView dicesTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
        this.dicesTestView = dicesTestView;
    }

    @Override
    protected String getRegex() {
        return "\"([^\"]+)\" should not be (\\d+)";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        if (match == null) return;
        var entityPropertyKey = PrettyKey.getKey(match[1]);

        var expectedValue = Integer.parseInt(match[2]);
        String dicesId = dicesTestView.getDicesId();

        var value = entityTestView.getEntityPropertyInt(dicesId, entityPropertyKey);
        assertThat(value).isNotEqualTo(expectedValue);
    }
}
