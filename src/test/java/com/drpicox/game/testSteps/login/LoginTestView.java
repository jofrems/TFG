package com.drpicox.game.testSteps.login;

import com.drpicox.game.common.api.GlobalRestException;
import com.drpicox.game.common.api.SuccessResponse;
import com.drpicox.game.players.api.LoginResponse;
import com.drpicox.game.testPost.SnapshotService;
import com.drpicox.game.testSteps.message.MessageTestView;
import com.drpicox.game.testSteps.navigator.NavigableScreen;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import com.drpicox.game.testSteps.player.PlayerTestView;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class LoginTestView implements NavigableScreen {
    private final NavigatorTestView navigatorTestView;
    private final SnapshotService snapshotService;
    private final MessageTestView messageTestView;
    private final PlayerTestView playerTestView;

    public LoginTestView(NavigatorTestView navigatorTestView, SnapshotService snapshotService, MessageTestView messageTestView, PlayerTestView playerTestView) {
        this.navigatorTestView = navigatorTestView;
        this.snapshotService = snapshotService;
        this.messageTestView = messageTestView;
        this.playerTestView = playerTestView;
    }

    private String playerName;
    private String password;

    public void enterPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void enterPassword(String password) {
        this.password = password;
    }

    public void submit() {
        var data = new HashMap<String, String>();
        data.put("playerName", playerName);
        data.put("password", password);

        var result = messageTestView.callApi(() -> {
            var response = snapshotService.post("/api/v1/players/login", data, LoginResponse.class);
            var responsePlayerName = response.getPlayerName();
            var token = response.getToken();

            playerTestView.replaceToken(responsePlayerName, token);
            return response;
        });
        if (result != null) navigatorTestView.pushScreenName("player");
    }

    @Override
    public String getScreenName() {
        return "login";
    }

    @Override
    public void show() {
        clear();
    }

    private void clear() {
        playerName = "";
        password = "";
    }
}
