package com.tfg.game.testSteps.signup;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class AddYourNameAsPlayerNameStep extends AbstractPostLineStep {

    private SignupTestView signupTestView;

    public AddYourNameAsPlayerNameStep(SignupTestView signupTestView) {
        this.signupTestView = signupTestView;
    }

    @Override
    protected String getRegex() {
        return "Add your name as player name";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        signupTestView.addPlayerName("leonard");
    }
}
