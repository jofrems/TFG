package com.tfg.game.testSteps.multiplayer;

import com.tfg.game.players.api.PlayersApi;
import com.tfg.game.players.api.SignupForm;
import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
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
