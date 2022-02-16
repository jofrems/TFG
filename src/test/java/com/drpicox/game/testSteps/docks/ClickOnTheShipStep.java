package com.drpicox.game.testSteps.docks;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.locateds.LocatedTestView;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.nameds.NamedTestView.byName;
import static com.drpicox.game.testSteps.owneds.OwnedTestView.byOwner;

@Component
public class ClickOnTheShipStep extends AbstractPostLineStep {

    private final LocatedTestView locatedTestView;
    private final NavigatorTestView navigatorTestView;

    public ClickOnTheShipStep(LocatedTestView locatedTestView, NavigatorTestView navigatorTestView) {
        this.locatedTestView = locatedTestView;
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Click on the \"([^\"]+)\" ship \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var ownerName = match[1];
        var shipName = match[2];

        var ship = locatedTestView.streamCoLocateds()
                .filter(byOwner(ownerName)).filter(byName(shipName))
                .findFirst().get();

        navigatorTestView.pushScreenName("entity", ship.getId());
    }
}
