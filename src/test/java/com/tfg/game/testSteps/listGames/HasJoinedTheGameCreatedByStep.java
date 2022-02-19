package com.tfg.game.testSteps.listGames;

import com.tfg.game.games.api.GamesApi;
import com.tfg.game.games.api.JoinGameForm;
import com.tfg.game.players.api.LoginForm;
import com.tfg.game.players.api.PlayersApi;
import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class HasJoinedTheGameCreatedByStep extends AbstractPostLineStep {

    private final PlayersApi playersApi;
    private final GamesApi gamesApi;

    public HasJoinedTheGameCreatedByStep(PlayersApi playersApi, GamesApi gamesApi) {
        this.playersApi = playersApi;
        this.gamesApi = gamesApi;
    }

    @Override
    protected String getRegex() {
        return "And \"([^\"]+)\" has joined the game \"([^\"]+)\" created by \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var gameName = match[2];
        var creatorName = match[3];

        var login = new LoginForm(playerName, "tbbt12");
        var token = playersApi.login(login).getToken();

        var joinGame = new JoinGameForm(gameName, creatorName, token);
        gamesApi.join(joinGame);
    }
}
