package com.tfg.game.testSteps.player;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class EnterAsFriendNameStep extends AbstractPostLineStep {

    private final PlayerTestView playerTestView;

    public EnterAsFriendNameStep(PlayerTestView playerTestView) {
        this.playerTestView = playerTestView;
    }

    @Override
    protected String getRegex() {
        return "Enter \"([^\"]*)\" as friend name";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var friendName = match[1];
        playerTestView.enterFriendName(friendName);
    }
}
