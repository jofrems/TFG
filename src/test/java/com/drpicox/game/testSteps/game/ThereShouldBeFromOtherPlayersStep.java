package com.drpicox.game.testSteps.game;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.game.TypedTestView.byType;
import static com.drpicox.game.testSteps.owneds.OwnedTestView.byOwner;
import static com.google.common.truth.Truth8.assertThat;
import static java.util.function.Predicate.not;

@Component
public class ThereShouldBeFromOtherPlayersStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;

    public ThereShouldBeFromOtherPlayersStep(GameTestView gameTestView) {
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be (\\d+) ([a-z]+) from other players";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedSize = Integer.parseInt(match[1]);
        var type = match[2];
        var playerName = gameTestView.getGame().getPlayerName();

        var found = gameTestView.streamEntities(byType(type).and(not(byOwner(playerName))));
        assertThat(found).hasSize(expectedSize);
    }
}
