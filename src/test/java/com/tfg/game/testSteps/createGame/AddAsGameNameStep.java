package com.tfg.game.testSteps.createGame;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class AddAsGameNameStep extends AbstractPostLineStep {

    private CreateGameTestView createGameTestView;

    public AddAsGameNameStep(CreateGameTestView createGameTestView) {
        this.createGameTestView = createGameTestView;
    }

    @Override
    protected String getRegex() {
        return "Add \"([^\"]+)\" as game name";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var gameName = match[1];
        createGameTestView.addGameName(gameName);
    }
}
