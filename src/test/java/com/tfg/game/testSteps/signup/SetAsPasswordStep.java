package com.tfg.game.testSteps.signup;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class SetAsPasswordStep extends AbstractPostLineStep {

    private SignupTestView signupTestView;

    public SetAsPasswordStep(SignupTestView signupTestView) {
        this.signupTestView = signupTestView;
    }

    @Override
    protected String getRegex() {
        return "Set \"([^\"]+)\" as password";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var password = match[1];
        signupTestView.setPassword(password);
    }
}
