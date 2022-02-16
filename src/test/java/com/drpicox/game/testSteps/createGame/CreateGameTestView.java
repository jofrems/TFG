package com.drpicox.game.testSteps.createGame;

import com.drpicox.game.common.api.SuccessResponse;
import com.drpicox.game.testPost.SnapshotService;
import com.drpicox.game.testSteps.message.MessageTestView;
import com.drpicox.game.testSteps.navigator.NavigableScreen;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import com.drpicox.game.testSteps.player.PlayerTestView;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CreateGameTestView implements NavigableScreen {
    private final NavigatorTestView navigatorTestView;
    private final MessageTestView messageTestView;
    private final PlayerTestView playerTestView;
    private final SnapshotService snapshotService;

    public CreateGameTestView(NavigatorTestView navigatorTestView, MessageTestView messageTestView, PlayerTestView playerTestView, SnapshotService snapshotService) {
        this.navigatorTestView = navigatorTestView;
        this.messageTestView = messageTestView;
        this.playerTestView = playerTestView;
        this.snapshotService = snapshotService;
    }

    private String gameName;

    public void addGameName(String gameName) {
        this.gameName = gameName;
    }

    public void submit() {
        var token = playerTestView.getToken();

        var data = new HashMap<String, String>();
        data.put("gameName", gameName);
        data.put("token", token);

        var response = messageTestView.callApi(() -> snapshotService.post("/api/v1/games", data, SuccessResponse.class));
        if (response != null) navigatorTestView.popScreenName();
    }

    @Override
    public String getScreenName() {
        return "create game";
    }

    @Override
    public void show() {
        clear();
    }

    private void clear() {
        gameName = "";
    }

}
