package com.drpicox.game.testPost.reader;

import java.util.ArrayList;
import java.util.List;

public class PostSubsection {
    private final List<PostLine> lines = new ArrayList<>();
    private final String name;

    public PostSubsection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addLine(PostLine line) {
        lines.add(line);
    }

    public List<PostLine> getLines() {
        return lines;
    }

    public PostLine getFirstLine() {
        return lines.get(0);
    }

}
