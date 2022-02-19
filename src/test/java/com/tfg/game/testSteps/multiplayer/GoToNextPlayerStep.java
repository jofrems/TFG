package com.tfg.game.testSteps.multiplayer;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class GoToNextPlayerStep extends AbstractPostLineStep {

    private MultiplayerTestView multiplayerTestView;

    public GoToNextPlayerStep(MultiplayerTestView multiplayerTestView) {
        this.multiplayerTestView = multiplayerTestView;
    }

    @Override
    protected String getRegex() {
        return "Go to Next player";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        multiplayerTestView.next();
    }
}
