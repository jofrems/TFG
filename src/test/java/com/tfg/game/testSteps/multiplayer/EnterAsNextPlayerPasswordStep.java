package com.tfg.game.testSteps.multiplayer;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class EnterAsNextPlayerPasswordStep extends AbstractPostLineStep {

    private final AddNextPlayerTestView addNextPlayerTestView;

    public EnterAsNextPlayerPasswordStep(AddNextPlayerTestView addNextPlayerTestView) {
        this.addNextPlayerTestView = addNextPlayerTestView;
    }

    @Override
    protected String getRegex() {
        return "Enter \"([^\"]+)\" as next player password";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var password = match[1];
        addNextPlayerTestView.enterNextPlayerPassword(password);
    }
}
