package com.drpicox.game.testSteps.signup;

import com.drpicox.game.common.api.SuccessResponse;
import com.drpicox.game.testPost.SnapshotService;
import com.drpicox.game.testSteps.message.MessageTestView;

import com.drpicox.game.testSteps.navigator.NavigableScreen;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SignupTestView implements NavigableScreen  {
    private final NavigatorTestView navigatorTestView;
    private final SnapshotService snapshotService;
    private final MessageTestView messageTestView;

    public SignupTestView(NavigatorTestView navigatorTestView, SnapshotService snapshotService, MessageTestView messageTestView) {
        this.navigatorTestView = navigatorTestView;
        this.snapshotService = snapshotService;
        this.messageTestView = messageTestView;
    }

    private String playerName;
    private String password;

    public void addPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void signup() {
        var data = new HashMap<String, String>();
        data.put("playerName", playerName);
        data.put("password", password);

        var response = messageTestView.callApi(() -> snapshotService.post("/api/v1/players", data, SuccessResponse.class));
        if (response != null) navigatorTestView.popScreenName();
    }

    @Override
    public String getScreenName() {
        return "signup";
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
