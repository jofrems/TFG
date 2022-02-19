package com.tfg.game.testSteps.game;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class TheShouldBeStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;

    public TheShouldBeStep(EntityTestView entityTestView) {
        this.entityTestView = entityTestView;
    }

    @Override
    protected String getRegex() {
        return "The ([a-z ]+) should be ((\\d+)|(active|inactive)|(\"([^\"]+)\"))";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var entityPropertyKey = PrettyKey.getKey(match[1]);

        mayRunWithNumber(entityPropertyKey, match[3]);
        mayRunWithActivation(entityPropertyKey, match[4]);
        mayRunWithString(entityPropertyKey, match[6]);
    }

    private void mayRunWithNumber(String key, String match) {
        if (match == null) return;
        var expectedValue = Integer.parseInt(match);

        var value = entityTestView.getEntityPropertyInt(key);
        assertThat(value).isEqualTo(expectedValue);
    }

    private void mayRunWithActivation(String key, String match) {
        if (match == null) return;
        var expectedValue = match.equals("active");

        var value = entityTestView.getEntityPropertyBoolean(key);
        assertThat(value).isEqualTo(expectedValue);
    }

    private void mayRunWithString(String key, String match) {
        if (match == null) return;
        var expectedValue = match;

        var value = entityTestView.getEntityPropertyString(key);
        assertThat(value).isEqualTo(expectedValue);
    }
}
