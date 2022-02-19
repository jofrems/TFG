package com.tfg.game.testSteps.listGames;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;

@Component
public class ThereShouldBeGameStep extends AbstractPostLineStep {

    private final ListGamesTestView listGamesTestView;

    public ThereShouldBeGameStep(ListGamesTestView listGamesTestView) {
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be (\\d+) game";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var size = Integer.parseInt(match[1]);
        var games = listGamesTestView.getGames();
        assertThat(games).hasSize(size);
    }
}
