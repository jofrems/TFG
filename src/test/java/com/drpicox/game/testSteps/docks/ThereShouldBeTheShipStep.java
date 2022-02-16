package com.drpicox.game.testSteps.docks;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.locateds.LocatedTestView;
import com.drpicox.game.testSteps.nameds.NamedTestView;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.owneds.OwnedTestView.byOwner;
import static com.google.common.truth.Truth8.assertThat;

@Component
public class ThereShouldBeTheShipStep extends AbstractPostLineStep {

    private final LocatedTestView locatedTestView;

    public ThereShouldBeTheShipStep(LocatedTestView locatedTestView) {
        this.locatedTestView = locatedTestView;
    }

    @Override
    protected String getRegex() {
        return "There should be the \"([^\"]+)\" \"([^\"]+)\" ship";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var ownerName = match[1];
        var expectedName = match[2];

        var names = locatedTestView.streamCoLocateds()
                .filter(byOwner(ownerName))
                .map(NamedTestView.toName());
        assertThat(names).contains(expectedName);
    }
}
