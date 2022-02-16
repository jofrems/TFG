package com.drpicox.game.testSteps.game;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.game.TypedTestView.byType;
import static com.drpicox.game.testSteps.nameds.NamedTestView.byName;
import static com.drpicox.game.testSteps.owneds.OwnedTestView.byOwner;

@Component
public class ClickOnTheOthersStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;
    private final GameTestView gameTestView;

    public ClickOnTheOthersStep(NavigatorTestView navigatorTestView, GameTestView gameTestView) {
        this.navigatorTestView = navigatorTestView;
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "Click on the others ([a-z]+) \"([^\"]+)\" \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var type = match[1];
        var ownerName = match[2];
        var name = match[3];

        var city = gameTestView.findEntity(byType(type).and(byOwner(ownerName)).and(byName(name))).get();

        navigatorTestView.pushScreenName("entity", city.getId());
    }
}
