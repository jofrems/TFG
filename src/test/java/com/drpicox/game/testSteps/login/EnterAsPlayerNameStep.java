package com.drpicox.game.testSteps.login;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class EnterAsPlayerNameStep extends AbstractPostLineStep {

    private final LoginTestView loginTestView;

    public EnterAsPlayerNameStep(LoginTestView loginTestView) {
        this.loginTestView = loginTestView;
    }

    @Override
    protected String getRegex() {
        return "Enter \"([^\"]+)\" as player name";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        loginTestView.enterPlayerName(playerName);
    }
}
