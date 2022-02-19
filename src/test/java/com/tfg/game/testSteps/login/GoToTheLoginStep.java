package com.tfg.game.testSteps.login;

import com.tfg.game.testSteps.navigator.NavigatorTestView;
import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class GoToTheLoginStep extends AbstractPostLineStep {

    private NavigatorTestView navigatorTestView;

    public GoToTheLoginStep(NavigatorTestView navigatorTestView) {
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Go to the login";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        navigatorTestView.pushScreenName("login");
    }
}
