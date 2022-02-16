package com.drpicox.game.testSteps.signup;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class AddAsPlayerNameStep extends AbstractPostLineStep {

    private SignupTestView signupTestView;

    public AddAsPlayerNameStep(SignupTestView signupTestView) {
        this.signupTestView = signupTestView;
    }

    @Override
    protected String getRegex() {
        return "Add \"([^\"]+)\" as player name";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        signupTestView.addPlayerName(playerName);
    }
}
