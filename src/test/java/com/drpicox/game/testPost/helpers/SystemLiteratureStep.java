package com.drpicox.game.testPost.helpers;

import com.drpicox.game.testPost.PostLineStep;
import com.drpicox.game.testPost.SystemPostLineStep;
import com.drpicox.game.testPost.reader.PostLine;
import org.springframework.stereotype.Component;

@Component
public class SystemLiteratureStep implements SystemPostLineStep {
    @Override
    public boolean match(PostLine line) {
        return line.match("^[^\\s]") != null;
    }

    @Override
    public void run(PostLine line) {
        // Do nothing
    }

    @Override
    public String getPrettyPrint() {
        return "Ignore lines that do not start with an space (" + getClass().getName() + ")";
    }
}
