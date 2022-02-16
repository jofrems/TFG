package com.drpicox.game.testSteps.docks;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.locateds.LocatedTestView;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.nameds.NamedTestView.byName;
import static com.drpicox.game.testSteps.owneds.OwnedTestView.byOwner;

@Component
public class ClickOnSeeTheDockStep extends AbstractPostLineStep {

    private final DockTestView dockTestView;
    private final NavigatorTestView navigatorTestView;

    public ClickOnSeeTheDockStep(DockTestView dockTestView, NavigatorTestView navigatorTestView) {
        this.dockTestView = dockTestView;
        this.navigatorTestView = navigatorTestView;
    }

    @Override
    protected String getRegex() {
        return "Click on see the dock";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var entityId = dockTestView.getCoLocatedDockId();

        navigatorTestView.pushScreenName("entity", entityId);
    }
}
