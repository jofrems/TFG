package com.drpicox.game.testSteps.docks;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.game.TypedTestView;
import com.drpicox.game.testSteps.nameds.NamedTestView;
import com.drpicox.game.testSteps.owneds.OwnedTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ItShouldBeDockedAtTheStep extends AbstractPostLineStep {


    private final DockTestView dockTestView;
    private final OwnedTestView ownedTestView;
    private final TypedTestView typedTestView;
    private final NamedTestView namedTestView;

    public ItShouldBeDockedAtTheStep(DockTestView dockTestView, OwnedTestView ownedTestView, TypedTestView typedTestView, NamedTestView namedTestView) {
        this.dockTestView = dockTestView;
        this.ownedTestView = ownedTestView;
        this.typedTestView = typedTestView;
        this.namedTestView = namedTestView;
    }

    @Override
    protected String getRegex() {
        return "It should be docked at the \"([^\"]+)\" ([a-z]+) \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedOwnerName = match[1];
        var expectedType = match[2];
        var expectedName = match[3];

        var entityId = dockTestView.getCoLocatedDockId();
        var ownerName = ownedTestView.getOnwer(entityId);
        var type = typedTestView.getType(entityId);
        var name = namedTestView.getName(entityId);

        assertThat(ownerName).isEqualTo(expectedOwnerName);
        assertThat(type).isEqualTo(expectedType);
        assertThat(name).isEqualTo(expectedName);
    }
}
