package com.drpicox.game.testSteps.game;

import com.drpicox.game.games.api.CreateGameForm;
import com.drpicox.game.games.api.GamesApi;
import com.drpicox.game.players.api.LoginForm;
import com.drpicox.game.players.api.PlayersApi;
import com.drpicox.game.players.api.SignupForm;
import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.multiplayer.MultiplayerTestView;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class ThereIsPlayingTheirGameStep extends AbstractPostLineStep {

    private final PlayersApi playersApi;
    private final GamesApi gamesApi;
    private final GameTestView gameTestView;
    private final NavigatorTestView navigatorTestView;
    private final MultiplayerTestView multiplayerTestView;

    public ThereIsPlayingTheirGameStep(PlayersApi playersApi, GamesApi gamesApi, GameTestView gameTestView, NavigatorTestView navigatorTestView, MultiplayerTestView multiplayerTestView) {
        this.playersApi = playersApi;
        this.gamesApi = gamesApi;
        this.gameTestView = gameTestView;
        this.navigatorTestView = navigatorTestView;
        this.multiplayerTestView = multiplayerTestView;
    }

    @Override
    protected String getRegex() {
        return "there is \"([^\"]+)\" playing their game \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var gameName = match[2];
        var password = "tbbt12";

        playersApi.signup(new SignupForm(playerName, password));
        var token = playersApi.login(new LoginForm(playerName, password)).getToken();
        multiplayerTestView.addToken(token);
        gamesApi.createGame(new CreateGameForm(gameName, token));

        gameTestView.fetchGame(gameName, playerName, token);
        navigatorTestView.pushScreenName("game");
    }
}
