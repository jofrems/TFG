package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.SnapshotService;
import com.tfg.game.testSteps.message.MessageTestView;
import com.tfg.game.testSteps.navigator.NavigableScreen;
import com.tfg.game.testSteps.player.PlayerTestView;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class GameTestView implements NavigableScreen {

    private final MessageTestView messageTestView;
    private final SnapshotService snapshotService;
    private final PlayerTestView playerTestView;

    public GameTestView(MessageTestView messageTestView, SnapshotService snapshotService, PlayerTestView playerTestView) {
        this.messageTestView = messageTestView;
        this.snapshotService = snapshotService;
        this.playerTestView = playerTestView;
    }

    private GameResponse game;

    /////////// --- NavigableScreen

    @Override
    public String getScreenName() {
        return "game";
    }

    @Override
    public void show() {
    }

    /////////// --- Current Saved Game

    public GameResponse getGame() {
        return game;
    }

    private void replaceGame(GameResponse game) {
        if (game == null) return;
        this.game = game;
        playerTestView.replaceToken(game.getPlayerName(), game.getToken());
    }

    public Stream<EntityResponse> streamEntities() {
        return game.streamEntities();
    }

    public Stream<EntityResponse> streamEntities(Predicate<EntityResponse> predicate) {
        return streamEntities().filter(predicate);
    }

    public Optional<EntityResponse> findEntity(Predicate<EntityResponse> predicate) {
        return streamEntities(predicate).findFirst();
    }

    public int countEntities(Predicate<EntityResponse> predicate) {
        return (int) streamEntities(predicate).count();
    }

    /////////// --- RestMethods

    public GameResponse fetchGame(String gameName, String creatorName, String token) {
        var game = messageTestView.callApi(
                () -> snapshotService.get("/api/v1/games/" + gameName + "/by/" + creatorName + "?token=" + token, null, GameResponse.class)
        );
        replaceGame(game);

        return game;
    }

    public GameResponse post(String url, Map data) {
        var token = playerTestView.getToken();

        var finalUrl = url + "?token=" + token;
        var result = messageTestView.callApi(() -> {
            var response = snapshotService.post(finalUrl, data, GameResponse.class);
            replaceGame(response);
            return response;
        });
        return result;
    }

    public void submitEndTheRound() {
        var gameName = game.getGameName();
        var creatorName = game.getCreatorName();
        var url = "/api/v1/games/" + gameName + "/by/" + creatorName + "/endRound";
        post(url, new LinkedHashMap<>());
    }

    public void submitRefresh() {
        var gameName = game.getGameName();
        var creatorName = game.getCreatorName();
        var token = game.getToken();

        fetchGame(gameName, creatorName, token);
    }
}
