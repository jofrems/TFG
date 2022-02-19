package com.tfg.game.testSteps.createGame;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.listGames.ListGamesTestView;
import com.tfg.game.testSteps.player.PlayerTestView;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static com.google.common.truth.Truth8.assertThat;

@Component
public class YouShouldHaveJoinedTheGameStep extends AbstractPostLineStep {

    private final PlayerTestView playerTestView;
    private final ListGamesTestView listGamesTestView;

    public YouShouldHaveJoinedTheGameStep(PlayerTestView playerTestView, ListGamesTestView listGamesTestView) {
        this.playerTestView = playerTestView;
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "You should have (not )?joined the game \"([^\"]+)\" created by \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var joinedName = playerTestView.getPlayerName();
        var gameName = match[2];
        var creatorName = match[3];
        var not = match[1];

        var gameFound = listGamesTestView.getGames().stream()
                .filter(g -> g.getGameName().equals(gameName))
                .filter(g -> g.getCreatorName().equals(creatorName))
                .findFirst();

        assertThat(gameFound).isPresent();
        var game = gameFound.get();

        Stream<String> joinedPlayerNames = game.getJoinedPlayerNames().stream();
        if (not == null) assertThat(joinedPlayerNames).contains(joinedName);
        else  assertThat(joinedPlayerNames).doesNotContain(joinedName);
    }
}
