package com.tfg.game.testPost.helpers;

import com.tfg.game.testPost.SystemPostLineStep;
import com.tfg.game.testPost.reader.PostLine;
import org.springframework.stereotype.Component;

@Component
public class SystemEmptyLineStep implements SystemPostLineStep {
    @Override
    public boolean match(PostLine line) {
        return line.match("^\\s*$") != null;
    }

    @Override
    public void run(PostLine line) {
        // Do nothing
    }

    @Override
    public String getPrettyPrint() {
        return "Ignore lines that are all spaces (" + getClass().getName() + ")";
    }
}
