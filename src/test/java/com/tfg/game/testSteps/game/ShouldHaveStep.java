package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.tfg.game.testSteps.game.TypedTestView.byType;
import static com.tfg.game.testSteps.owneds.OwnedTestView.byOwner;
import static com.google.common.truth.Truth8.assertThat;

@Component
public class ShouldHaveStep extends AbstractPostLineStep {


    private final GameTestView gameTestView;

    public ShouldHaveStep(GameTestView gameTestView) {
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "\"([^\"]+)\" should have (\\d+) ([^\"]+)";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var count = Integer.parseInt(match[2]);

        var inventory = gameTestView.streamEntities(byOwner(playerName).and(byType("inventory")));
        assertThat(inventory).hasSize(count);
    }
}
