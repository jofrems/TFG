package com.tfg.game.testSteps.listGames;

import com.tfg.game.games.api.ListGamesResponseEntry;
import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;

@Component
public class YouShouldSeeTheGameStep extends AbstractPostLineStep {

    private final ListGamesTestView listGamesTestView;

    public YouShouldSeeTheGameStep(ListGamesTestView listGamesTestView) {
        this.listGamesTestView = listGamesTestView;
    }

    @Override
    protected String getRegex() {
        return "You should see the game \"([^\"]+)\"( created by \"([^\"]+)\")?";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var gameName = match[1];
        var creatorName = match[3];

        var games = listGamesTestView.getGames();
        if (creatorName == null) runWithoutCreator(games, gameName);
        else runWithCreator(games, gameName, creatorName);
    }

    private void runWithoutCreator(List<ListGamesResponseEntry> games, String gameName) {
        var gameNames = games.stream().map(g -> g.getGameName()).collect(Collectors.toList());
        assertThat(gameNames).contains(gameName);
    }

    private void runWithCreator(List<ListGamesResponseEntry> games, String gameName, String creatorName) {
        var pairs = games.stream()
                .map(g -> g.getGameName() + "#" + g.getCreatorName())
                .collect(Collectors.toList());

        assertThat(pairs).contains(gameName + "#" + creatorName);
    }

}
