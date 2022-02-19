package com.tfg.game.testSteps.multiplayer;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class EnterAsNextPlayerNameStep extends AbstractPostLineStep {

    private final AddNextPlayerTestView addNextPlayerTestView;

    public EnterAsNextPlayerNameStep(AddNextPlayerTestView addNextPlayerTestView) {
        this.addNextPlayerTestView = addNextPlayerTestView;
    }

    @Override
    protected String getRegex() {
        return "Enter \"([^\"]+)\" as next player name";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        addNextPlayerTestView.enterNextPlayerName(playerName);
    }
}
