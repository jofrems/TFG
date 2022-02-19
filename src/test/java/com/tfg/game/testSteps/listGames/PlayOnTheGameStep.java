package com.tfg.game.testSteps.listGames;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.player.PlayerTestView;
import org.springframework.stereotype.Component;

@Component
public class PlayOnTheGameStep extends AbstractPostLineStep {

    private final PlayerTestView playerTestView;
    private final ListGamesTestView listGamesTestView;

    public PlayOnTheGameStep(PlayerTestView playerTestView, ListGamesTestView listGamesTestView) {
        this.playerTestView = playerTestView;
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "Play on the game \"([^\"]+)\" created by \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var gameName = match[1];
        var creatorName = match[2];

        var token = playerTestView.getToken();
        listGamesTestView.play(gameName, creatorName, token);
    }
}
