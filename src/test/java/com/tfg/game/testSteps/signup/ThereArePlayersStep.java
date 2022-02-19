package com.tfg.game.testSteps.signup;

import com.tfg.game.players.api.PlayersApi;
import com.tfg.game.players.api.SignupForm;
import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ThereArePlayersStep extends AbstractPostLineStep {

    private PlayersApi playersApi;

    public ThereArePlayersStep(PlayersApi playersApi) {
        this.playersApi = playersApi;
    }

    @Override
    protected String getRegex() {
        return "there are players (\"([^\"]+)\"( and|,)?)+";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var parts = line.getContent().split("\"");
        var playerNames = IntStream.range(0, parts.length)
                .filter(index -> index % 2 == 1)
                .mapToObj(index -> parts[index])
                .collect(Collectors.toList());

        playerNames.forEach(playerName -> playersApi.signup(new SignupForm(playerName, "tbbt12")));
    }
}
