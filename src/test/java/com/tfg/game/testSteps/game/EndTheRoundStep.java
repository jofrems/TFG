package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class EndTheRoundStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;
    private final NavigatorTestView navigatorTestView;

    public EndTheRoundStep(GameTestView gameTestView, NavigatorTestView navigatorTestView) {
        this.gameTestView = gameTestView;
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "End the round";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        gameTestView.submitEndTheRound();
        navigatorTestView.pushScreenName("game");
    }
}
