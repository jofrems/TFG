package com.tfg.game.testSteps.welcome;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class GoToTheWelcomScreenStep extends AbstractPostLineStep {

    private NavigatorTestView navigatorTestView;

    public GoToTheWelcomScreenStep(NavigatorTestView navigatorTestView) {
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Go to the Welcome screen";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        navigatorTestView.pushScreenName("welcome");
    }
}
