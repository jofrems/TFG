package com.drpicox.game.testSteps.game;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.game.TypedTestView.byType;
import static com.drpicox.game.testSteps.owneds.OwnedTestView.byOwner;
import static com.google.common.truth.Truth8.assertThat;
import static java.util.function.Predicate.not;

@Component
public class ThereShouldBeFromStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;

    public ThereShouldBeFromStep(GameTestView gameTestView) {
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be (\\d+) ([a-z]+) from \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedSize = Integer.parseInt(match[1]);
        var type = match[2];
        var playerName = match[3];

        var found = gameTestView.streamEntities(byType(type).and(byOwner(playerName)));
        assertThat(found).hasSize(expectedSize);
    }
}
