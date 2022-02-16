package com.drpicox.game.testSteps.message;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;
import static com.google.common.truth.Truth.assertThat;

@Component
public class YouShouldSeeAMessageSayingThatStep extends AbstractPostLineStep {

    private MessageTestView messageTestView;

    public YouShouldSeeAMessageSayingThatStep(MessageTestView messageTestView) {
        this.messageTestView = messageTestView;
    }

    @Override
    protected String getRegex() {
        return "You should see a message saying that \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var message = match[1];

        assertThat(messageTestView.getMessage()).isEqualTo(message);
    }
}
