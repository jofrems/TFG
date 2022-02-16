package com.drpicox.game.testSteps.player;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.login.LoginTestView;
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
