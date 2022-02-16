package com.drpicox.game.testSteps.sails;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.nameds.NamedTestView;
import org.springframework.stereotype.Component;

@Component
public class ClickTheDestinationSailButtonStep extends AbstractPostLineStep {

    private final SailTestView sailTestView;

    public ClickTheDestinationSailButtonStep(SailTestView sailTestView) {
        this.sailTestView = sailTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the destination sail button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        sailTestView.submitSail();
    }
}
