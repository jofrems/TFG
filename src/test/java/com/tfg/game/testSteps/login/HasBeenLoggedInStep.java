package com.tfg.game.testSteps.login;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import com.tfg.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class HasBeenLoggedInStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;
    private final LoginTestView loginTestView;

    public HasBeenLoggedInStep(NavigatorTestView navigatorTestView, LoginTestView loginTestView) {
        this.navigatorTestView = navigatorTestView;
        this.loginTestView = loginTestView;
    }

    @Override
    protected String getRegex() {
        return "\"([^\"]+)\" has been logged in";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var password = "tbbt12";
        
        navigatorTestView.pushScreenName("login");
        loginTestView.enterPlayerName(playerName);
        loginTestView.enterPassword(password);
        loginTestView.submit();
    }
}
