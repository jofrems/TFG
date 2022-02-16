package com.drpicox.game.testSteps.login;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class ClickTheLoginButtonStep extends AbstractPostLineStep {

    private LoginTestView loginTestView;

    public ClickTheLoginButtonStep(LoginTestView loginTestView) {
        this.loginTestView = loginTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the login button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        loginTestView.submit();
    }
}
