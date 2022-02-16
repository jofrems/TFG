package com.drpicox.game.testSteps.message;

import com.drpicox.game.common.api.GlobalRestException;
import com.drpicox.game.common.api.ResponseWithMessage;
import com.drpicox.game.common.api.SuccessResponse;
import org.springframework.stereotype.Component;

@Component
public class MessageTestView {
    private String message;
    private boolean isError;

    public void reportMessage(String message) {
        this.message = message;
        isError = false;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return isError;
    }

    public void reportError(GlobalRestException g) {
        this.message = g.getMessage();
        this.isError = true;
    }

    public <T> T callApi(MessageApiCaller<T> apiCaller) {
        try {
            var response = apiCaller.callApi();
            if (response instanceof ResponseWithMessage)
                this.reportMessage(((ResponseWithMessage) response).getMessage());
            return response;
        } catch (GlobalRestException g) {
            reportError(g);
            return null;
        }
    }
}
