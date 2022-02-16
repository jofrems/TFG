package com.drpicox.game.testSteps.createGame;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class ClickTheCreateGameButtonStep extends AbstractPostLineStep {

    private CreateGameTestView createGameTestView;

    public ClickTheCreateGameButtonStep(CreateGameTestView createGameTestView) {
        this.createGameTestView = createGameTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the create game button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        createGameTestView.submit();
    }
}
