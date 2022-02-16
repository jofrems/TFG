package com.drpicox.game.testSteps.game;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class EnterAsStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;

    public EnterAsStep(EntityTestView entityTestView) {
        this.entityTestView = entityTestView;
    }

    @Override
    protected String getRegex() {
        return "Enter ([a-z ]+) as (\\d+)";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var key = PrettyKey.getKey(match[1]);
        var value = Integer.parseInt(match[2]);

        entityTestView.putFormKey(key, value);
    }
}
