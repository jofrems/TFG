package com.drpicox.game.testSteps.login;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class YouHaveBeenLoggedInStep extends AbstractPostLineStep {

    private final LoginTestView loginTestView;
    private final NavigatorTestView navigatorTestView;

    public YouHaveBeenLoggedInStep(LoginTestView loginTestView, NavigatorTestView navigatorTestView) {
        this.loginTestView = loginTestView;
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "you have been logged in";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        navigatorTestView.pushScreenName("login");
        loginTestView.enterPlayerName("leonard");
        loginTestView.enterPassword("tbbt12");
        loginTestView.submit();
    }
}
