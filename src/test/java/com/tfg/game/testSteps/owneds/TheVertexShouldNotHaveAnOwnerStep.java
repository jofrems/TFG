package com.tfg.game.testSteps.owneds;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class TheVertexShouldNotHaveAnOwnerStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final OwnedTestView ownedTestView;

    public TheVertexShouldNotHaveAnOwnerStep(EntityTestView entityTestView, OwnedTestView ownedTestView) {
        this.entityTestView = entityTestView;
        this.ownedTestView = ownedTestView;
    }

    @Override
    protected String getRegex() {
        return "The \"([^\"]+)\" should not have an owner";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        //var entityId = match[1];
        var entityId = ownedTestView.getVertexId();
        var owner = ownedTestView.getHasOwner(entityId);
        assertThat(owner).isFalse();
    }
}
