package com.drpicox.game.testSteps.signup;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class ClickTheSignupButtonStep extends AbstractPostLineStep {

    private SignupTestView signupTestView;

    public ClickTheSignupButtonStep(SignupTestView signupTestView) {
        this.signupTestView = signupTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the signup button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        signupTestView.signup();
    }
}
