package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class RefreshTheGameStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;

    public RefreshTheGameStep(GameTestView gameTestView) {
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "Refresh the game";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        gameTestView.submitRefresh();
    }
}
