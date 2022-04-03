package com.tfg.game.testSteps.partnerships;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testSteps.AbstractPostLineStep;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.owneds.OwnedTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class TheRoadShouldHaveVertexIdStep extends AbstractPostLineStep {

    private final EntityTestView entityTestView;
    private final PartnershipTestView partnershipTestView;

    public TheRoadShouldHaveVertexIdStep(EntityTestView entityTestView, PartnershipTestView partnershipTestView) {
        this.entityTestView = entityTestView;
        this.partnershipTestView = partnershipTestView;
    }

    @Override
    protected String getRegex() {
        return "The road should have (\\d+) vertex id";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedValue = Integer.parseInt(match[1]);
        var entityId = partnershipTestView.getRoadId();
        var count = partnershipTestView.getCountPartnerships(entityId);
        assertThat(count).isEqualTo(expectedValue);
    }
}
