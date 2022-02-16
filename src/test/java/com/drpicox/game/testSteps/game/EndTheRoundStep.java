package com.drpicox.game.testSteps.game;

import com.drpicox.game.games.api.CreateGameForm;
import com.drpicox.game.games.api.GamesApi;
import com.drpicox.game.players.api.LoginForm;
import com.drpicox.game.players.api.PlayersApi;
import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.listGames.ListGamesTestView;
import com.drpicox.game.testSteps.login.LoginTestView;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class EndTheRoundStep extends AbstractPostLineStep {

    private final GameTestView gameTestView;
    private final NavigatorTestView navigatorTestView;

    public EndTheRoundStep(GameTestView gameTestView, NavigatorTestView navigatorTestView) {
        this.gameTestView = gameTestView;
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "End the round";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        gameTestView.submitEndTheRound();
        navigatorTestView.pushScreenName("game");
    }
}
