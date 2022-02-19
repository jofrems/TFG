package com.tfg.game.testSteps.listGames;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class GoToMyPlayingGamesStep extends AbstractPostLineStep {

    private final ListGamesTestView listGamesTestView;

    public GoToMyPlayingGamesStep(ListGamesTestView listGamesTestView) {
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "Go to my playing games";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        listGamesTestView.fetchMyPlayingGames();
    }
}
