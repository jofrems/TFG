package com.tfg.game.testSteps.multiplayer;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.player.PlayerTestView;
import org.springframework.stereotype.Component;

@Component
public class ClickTheJoinNextButtonStep extends AbstractPostLineStep {

    private final AddNextPlayerTestView addNextPlayerTestView;
    private final MultiplayerTestView multiplayerTestView;
    private final PlayerTestView playerTestView;

    public ClickTheJoinNextButtonStep(AddNextPlayerTestView addNextPlayerTestView, MultiplayerTestView multiplayerTestView, PlayerTestView playerTestView) {
        this.addNextPlayerTestView = addNextPlayerTestView;
        this.multiplayerTestView = multiplayerTestView;
        this.playerTestView = playerTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the Join Next button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var previousToken = playerTestView.getToken();
        multiplayerTestView.addToken(previousToken);

        var response = addNextPlayerTestView.submit();
        if (response == null) return;

        var nextToken = response.getToken();
        multiplayerTestView.addToken(nextToken);
    }
}
