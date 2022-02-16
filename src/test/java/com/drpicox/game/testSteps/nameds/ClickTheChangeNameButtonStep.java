package com.drpicox.game.testSteps.nameds;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ClickTheChangeNameButtonStep extends AbstractPostLineStep {

    private final NamedTestView namedTestView;

    public ClickTheChangeNameButtonStep(NamedTestView namedTestView) {
        this.namedTestView = namedTestView;
    }

    @Override
    protected String getRegex() {
        return "Click the change name button";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        namedTestView.submitChangeCityName();
    }
}
