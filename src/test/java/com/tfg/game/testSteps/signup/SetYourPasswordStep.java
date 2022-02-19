package com.tfg.game.testSteps.signup;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class SetYourPasswordStep extends AbstractPostLineStep {

    private SignupTestView signupTestView;

    public SetYourPasswordStep(SignupTestView signupTestView) {
        this.signupTestView = signupTestView;
    }

    @Override
    protected String getRegex() {
        return "Set your password";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        signupTestView.setPassword("tbbt12");
    }
}
