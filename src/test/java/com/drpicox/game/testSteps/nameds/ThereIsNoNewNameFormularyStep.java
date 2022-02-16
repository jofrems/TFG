package com.drpicox.game.testSteps.nameds;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

@Component
public class ThereIsNoNewNameFormularyStep extends AbstractPostLineStep {

    @Override
    protected String getRegex() {
        return "There is no new city name formulary";
    }

    @Override
    protected void run(PostLine line, String[] match) {
    }
}
