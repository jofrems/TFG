package com.tfg.game.testSteps.message;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

@Component
public class YouShouldSeeAnErrorMessageSayingThatStep extends AbstractPostLineStep {

    private MessageTestView messageTestView;

    public YouShouldSeeAnErrorMessageSayingThatStep(MessageTestView messageTestView) {
        this.messageTestView = messageTestView;
    }

    @Override
    protected String getRegex() {
        return "You should see an error message saying that \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var message = match[1];

        assertThat(messageTestView.getMessage()).isEqualTo(message);
        assertWithMessage("Message should be an error. Message.isError(): ").that(messageTestView.isError()).isTrue();
    }
}
