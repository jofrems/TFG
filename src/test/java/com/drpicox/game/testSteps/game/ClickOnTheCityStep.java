package com.drpicox.game.testSteps.game;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.navigator.NavigatorTestView;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.game.TypedTestView.byType;
import static com.drpicox.game.testSteps.nameds.NamedTestView.byName;
import static com.drpicox.game.testSteps.owneds.OwnedTestView.byOwner;

@Component
public class ClickOnTheCityStep extends AbstractPostLineStep {

    private final NavigatorTestView navigatorTestView;
    private final GameTestView gameTestView;

    public ClickOnTheCityStep(NavigatorTestView navigatorTestView, GameTestView gameTestView) {
        this.navigatorTestView = navigatorTestView;
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "Click on the \"([^\"]+)\" city \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var ownerName = match[1];
        var cityName = match[2];

        // TODO: search by type
        var game = gameTestView.getGame();
        var city = gameTestView.findEntity(byType("city").and(byOwner(ownerName)).and(byName(cityName))).get();

        navigatorTestView.pushScreenName("entity", city.getId());
    }
}
