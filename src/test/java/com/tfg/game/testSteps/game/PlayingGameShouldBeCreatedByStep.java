package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class PlayingGameShouldBeCreatedByStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;

    public PlayingGameShouldBeCreatedByStep(GameTestView gameTestView) {
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "Playing game should be \"([^\"]+)\" created by \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var gameName = match[1];
        var creatorName = match[2];

        var game = gameTestView.getGame();

        assertThat(game.getGameName()).isEqualTo(gameName);
        assertThat(game.getCreatorName()).isEqualTo(creatorName);
    }
}
