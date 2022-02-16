package com.drpicox.game.testSteps.loadable;

import com.drpicox.game.testSteps.game.EntityTestView;
import com.drpicox.game.testSteps.game.GameTestView;
import com.drpicox.game.testSteps.locateds.LocatedTestView;
import org.springframework.stereotype.Component;

import static com.drpicox.game.testSteps.docks.DockTestView.isDock;

@Component
public class LoadableTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;
    private final LocatedTestView locatedTestView;

    public LoadableTestView(EntityTestView entityTestView, GameTestView gameTestView, LocatedTestView locatedTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
        this.locatedTestView = locatedTestView;
    }

    public void submitLoad() {
        submitUnloadUnload("load");
    }

    public void submitUnload() {
        submitUnloadUnload("unload");
    }

    private void submitUnloadUnload(String action) {
        var sourceEntityId = getSourceEntityId();
        entityTestView.putFormKey("sourceEntityId", sourceEntityId);
        entityTestView.post("loadables", action);
    }

    private String getSourceEntityId() {
        return gameTestView.streamEntities(isDock().and(locatedTestView.byCoLocation()))
                .findFirst().get().getId();
    }
}
