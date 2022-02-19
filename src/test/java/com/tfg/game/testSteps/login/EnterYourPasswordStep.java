package com.tfg.game.testSteps.login;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class EnterYourPasswordStep extends AbstractPostLineStep {

    private final LoginTestView loginTestView;

    public EnterYourPasswordStep(LoginTestView loginTestView) {
        this.loginTestView = loginTestView;
    }

    @Override
    protected String getRegex() {
        return "Enter your password";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        loginTestView.enterPassword("tbbt12");
    }
}
