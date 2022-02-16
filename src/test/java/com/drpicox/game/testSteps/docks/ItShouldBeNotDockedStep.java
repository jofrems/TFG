package com.drpicox.game.testSteps.docks;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth8.assertThat;

@Component
public class ItShouldBeNotDockedStep extends AbstractPostLineStep {


    private final DockTestView dockTestView;

    public ItShouldBeNotDockedStep(DockTestView dockTestView) {
        this.dockTestView = dockTestView;
    }

    @Override
    protected String getRegex() {
        return "It should be not docked";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var mayEntityId = dockTestView.findCoLocatedDockId();
        assertThat(mayEntityId).isEmpty();
    }
}
