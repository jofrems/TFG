package com.drpicox.game.testSteps.multiplayer;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.login.LoginTestView;
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
