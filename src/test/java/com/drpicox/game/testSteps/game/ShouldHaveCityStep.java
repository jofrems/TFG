package com.drpicox.game.testSteps.game;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.game.TypedTestView.byType;
import static com.drpicox.game.testSteps.owneds.OwnedTestView.byOwner;
import static com.google.common.truth.Truth8.assertThat;

@Component
public class ShouldHaveCityStep extends AbstractPostLineStep {


    private final GameTestView gameTestView;

    public ShouldHaveCityStep(GameTestView gameTestView) {
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "\"([^\"]+)\" should have (\\d+) city";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var count = Integer.parseInt(match[2]);

        var cities = gameTestView.streamEntities(byOwner(playerName).and(byType("city")));
        assertThat(cities).hasSize(count);
    }
}
