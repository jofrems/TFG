package com.drpicox.game.testSteps.navigator;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

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
