package com.drpicox.game.testSteps.login;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

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
