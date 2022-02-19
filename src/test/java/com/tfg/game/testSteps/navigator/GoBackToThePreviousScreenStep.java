package com.tfg.game.testSteps.navigator;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class GoBackToThePreviousScreenStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;

    public GoBackToThePreviousScreenStep(NavigatorTestView navigatorTestView) {
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Go back to the previous screen";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        navigatorTestView.popScreenName();
    }
}
