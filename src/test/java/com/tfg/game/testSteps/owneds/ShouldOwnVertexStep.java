package com.tfg.game.testSteps.owneds;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ShouldOwnVertexStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final OwnedTestView ownedTestView;

    public ShouldOwnVertexStep(EntityTestView entityTestView, OwnedTestView ownedTestView) {
        this.entityTestView = entityTestView;
        this.ownedTestView = ownedTestView;
    }

    @Override
    protected String getRegex() {
        return "\"([^\"]+)\" should own \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        //var entityId = match[1];
        var entityId = ownedTestView.getVertexId();
        var owner = ownedTestView.getOwner(entityId);
        assertThat(owner).matches(match[1]);
    }
}
