package com.tfg.game.testSteps.listGames;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class JoinOnTheGameStep extends AbstractPostLineStep {

    private final ListGamesTestView listGamesTestView;

    public JoinOnTheGameStep(ListGamesTestView listGamesTestView) {
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "Join on the game \"([^\"]+)\" created by \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var gameName = match[1];
        var creatorName = match[2];

        listGamesTestView.join(gameName, creatorName);
    }
}
