package com.drpicox.game.testSteps.game;

import com.drpicox.game.games.api.GamesApi;
import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class SkipRoundsStep extends AbstractPostLineStep {

    private final GamesApi gamesApi;
    private final GameTestView gameTestView;
    private final NavigatorTestView navigatorTestView;

    public SkipRoundsStep(GamesApi gamesApi, GameTestView gameTestView, NavigatorTestView navigatorTestView) {
        this.gamesApi = gamesApi;
        this.gameTestView = gameTestView;
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Skip (\\d+) rounds";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var count = Integer.parseInt(match[1]);
        var game = gameTestView.getGame();
        var gameName = game.getGameName();
        var creatorName = game.getCreatorName();
        var token = game.getToken();

        for (var i = 0; i < count - 1; i++) {
            gamesApi.endRound(gameName, creatorName, token);
        }

        gameTestView.submitEndTheRound();
        navigatorTestView.pushScreenName("game");
    }
}
