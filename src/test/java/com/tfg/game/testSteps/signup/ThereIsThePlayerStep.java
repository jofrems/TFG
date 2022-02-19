package com.tfg.game.testSteps.signup;

import com.tfg.game.players.api.SignupForm;
import com.tfg.game.players.api.PlayersApi;
import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ThereIsThePlayerStep extends AbstractPostLineStep {

    private final PlayersApi playersApi;

    public ThereIsThePlayerStep(PlayersApi playersApi) {
        this.playersApi = playersApi;
    }

    @Override
    protected String getRegex() {
        return "there is the player \"([^\"]+)\"( with password \"([^\"]+)\")?";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var password = match[3] != null ? match[3] : "tbbt12";

        playersApi.signup(new SignupForm(playerName, password));
    }
}
