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
public class IsPlayingTheirGameStep extends AbstractPostLineStep {

    private final PlayersApi playersApi;
    private final GamesApi gamesApi;
    private final NavigatorTestView navigatorTestView;
    private final LoginTestView loginTestView;
    private final ListGamesTestView listGamesTestView;

    public IsPlayingTheirGameStep(PlayersApi playersApi, GamesApi gamesApi, NavigatorTestView navigatorTestView, LoginTestView loginTestView, ListGamesTestView listGamesTestView) {
        this.playersApi = playersApi;
        this.gamesApi = gamesApi;
        this.navigatorTestView = navigatorTestView;
        this.loginTestView = loginTestView;
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "\"([^\"]+)\" is playing their game \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var password = "tbbt12";
        var gameName = match[2];
        var creatorName = playerName;

        var token = playersApi.login(new LoginForm(playerName, password)).getToken();
        gamesApi.createGame(new CreateGameForm(gameName, token));
        listGamesTestView.play(gameName, creatorName, token);
    }
}
