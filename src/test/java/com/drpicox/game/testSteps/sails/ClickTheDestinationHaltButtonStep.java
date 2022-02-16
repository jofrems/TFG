package com.drpicox.game.testSteps.sails;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ClickTheDestinationHaltButtonStep extends AbstractPostLineStep {

    private final SailTestView sailTestView;

    public ClickTheDestinationHaltButtonStep(SailTestView sailTestView) {
        this.sailTestView = sailTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the destination halt button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        sailTestView.submitHalt();
    }
}
