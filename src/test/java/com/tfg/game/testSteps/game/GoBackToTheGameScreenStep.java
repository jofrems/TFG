package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class GoBackToTheGameScreenStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;

    public GoBackToTheGameScreenStep(NavigatorTestView navigatorTestView) {
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Go back to the game screen";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        navigatorTestView.pushScreenName("game");
    }
}
