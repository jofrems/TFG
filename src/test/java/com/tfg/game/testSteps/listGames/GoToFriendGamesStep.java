package com.tfg.game.testSteps.listGames;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.player.PlayerTestView;
import org.springframework.stereotype.Component;

@Component
public class GoToFriendGamesStep extends AbstractPostLineStep {

    private final PlayerTestView playerTestView;
    private final ListGamesTestView listGamesTestView;

    public GoToFriendGamesStep(PlayerTestView playerTestView, ListGamesTestView listGamesTestView) {
        this.playerTestView = playerTestView;
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "Go to friend games";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var friendName = playerTestView.getFriendName();
        listGamesTestView.fetch(friendName);
    }
}
