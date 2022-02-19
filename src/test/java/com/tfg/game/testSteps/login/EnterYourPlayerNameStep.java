package com.tfg.game.testSteps.login;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class EnterYourPlayerNameStep extends AbstractPostLineStep {

    private LoginTestView loginTestView;

    public EnterYourPlayerNameStep(LoginTestView loginTestView) {
        this.loginTestView = loginTestView;
    }

    @Override
    protected String getRegex() {
        return "Enter your player name";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        loginTestView.enterPlayerName("leonard");
    }
}
