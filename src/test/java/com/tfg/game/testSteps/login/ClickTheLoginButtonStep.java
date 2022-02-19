package com.tfg.game.testSteps.login;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

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
