package com.drpicox.game.testSteps.nameds;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.login.LoginTestView;
import org.springframework.stereotype.Component;

@Component
public class EnterNewNameStep extends AbstractPostLineStep {

    private final NamedTestView namedTestView;

    public EnterNewNameStep(NamedTestView namedTestView) {
        this.namedTestView = namedTestView;
    }

    @Override
    protected String getRegex() {
        return "Enter new name \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var newNamedName = match[1];
        namedTestView.enterNewName(newNamedName);
    }
}
