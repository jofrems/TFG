package com.tfg.game.testSteps.signup;

import com.tfg.game.players.api.SignupForm;
import com.tfg.game.players.api.PlayersApi;
import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ThereIsYourPlayerStep extends AbstractPostLineStep {

    private PlayersApi playersApi;

    public ThereIsYourPlayerStep(PlayersApi playersApi) {
        this.playersApi = playersApi;
    }

    @Override
    protected String getRegex() {
        return "there is your player";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        playersApi.signup(new SignupForm("leonard", "tbbt12"));
    }
}
