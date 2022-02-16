package com.drpicox.game.testSteps.multiplayer;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
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
