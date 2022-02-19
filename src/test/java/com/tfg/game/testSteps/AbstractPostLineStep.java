package com.tfg.game.testSteps;

import com.tfg.game.testPost.reader.PostLine;
import com.tfg.game.testPost.PostLineStep;

public abstract class AbstractPostLineStep implements PostLineStep {
    private final String regex;

    public AbstractPostLineStep() {
        this.regex = getRegex();
    }

    @Override
    public boolean match(PostLine line) {
        return line.match(regex) != null;
    }

    @Override
    public void run(PostLine line) {
        var match = line.match(regex);
        run(line, match);
    }

    protected abstract String getRegex();
    protected abstract void run(PostLine line, String[] match);

    @Override
    public String getPrettyPrint() {
        return "Matches \"" + regex + "\" (" + this.getClass().getName() + ")";
    }
}
