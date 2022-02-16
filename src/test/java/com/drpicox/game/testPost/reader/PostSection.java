package com.drpicox.game.testPost.reader;

import java.util.*;

public class PostSection {
    private final Map<String, PostSubsection> subsections = new LinkedHashMap<>();
    private final PostContent content;
    private final String sectionName;

    public PostSection(PostContent content, String sectionName) {
        this.content = content;
        this.sectionName = sectionName;
    }

    public void addLine(String subsectionName, PostLine line) {
        if (!subsections.containsKey(subsectionName))
            subsections.put(subsectionName, new PostSubsection(subsectionName));

        var subsection = subsections.get(subsectionName);
        subsection.addLine(line);
    }

    public List<PostLine> getLines() {
        var lines = new ArrayList<PostLine>();
        for (var subsection: subsections.values())
            lines.addAll(subsection.getLines());

        return lines;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getPrettyName() {
        var firstLine = getFirstLine();
        return firstLine.getContent() + " (" + content.getPostId() + ")";
    }

    private PostLine getFirstLine() {
        var firstSubsection = subsections.values().stream().findFirst().get();
        var line = firstSubsection.getFirstLine();
        return line;
    }

}
