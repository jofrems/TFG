package com.drpicox.game.testSteps.multiplayer;

import com.drpicox.game.games.api.CreateGameForm;
import com.drpicox.game.games.api.GamesApi;
import com.drpicox.game.players.api.LoginForm;
import com.drpicox.game.players.api.PlayersApi;
import com.drpicox.game.players.api.SignupForm;
import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.game.GameTestView;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class ThereIsTheNextPlayerStep extends AbstractPostLineStep {

    private final PlayersApi playersApi;
    private final AddNextPlayerTestView addNextPlayerTestView;
    private final MultiplayerTestView multiplayerTestView;

    public ThereIsTheNextPlayerStep(PlayersApi playersApi, AddNextPlayerTestView addNextPlayerTestView, MultiplayerTestView multiplayerTestView) {
        this.playersApi = playersApi;
        this.addNextPlayerTestView = addNextPlayerTestView;
        this.multiplayerTestView = multiplayerTestView;
    }

    @Override
    protected String getRegex() {
        return "there is the next player \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var password = "tbbt12";

        playersApi.signup(new SignupForm(playerName, password));

        addNextPlayerTestView.enterNextPlayerName(playerName);
        addNextPlayerTestView.enterNextPlayerPassword(password);
        var token = addNextPlayerTestView.submit().getToken();
        multiplayerTestView.addToken(token);
    }
}
