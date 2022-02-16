package com.drpicox.game.testSteps.locateds;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.message.MessageTestView;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ItShouldBeAtTheLocationStep extends AbstractPostLineStep {

    private final LocatedTestView locatedTestView;

    public ItShouldBeAtTheLocationStep(LocatedTestView locatedTestView) {
        this.locatedTestView = locatedTestView;
    }

    @Override
    protected String getRegex() {
        return "It should be at the location (\\d+)";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var expectedLocation = Integer.parseInt(match[1]);

        var location = locatedTestView.getLocation();
        assertThat(location).isEqualTo(expectedLocation);
    }
}
