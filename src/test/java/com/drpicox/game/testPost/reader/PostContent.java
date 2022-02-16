package com.drpicox.game.testPost.reader;

import java.util.*;

public class PostContent {
    private final Map<String, PostSection> sections = new LinkedHashMap<>();
    private final String postId;

    public PostContent(String postId) {
        this.postId = postId;
    }

    public String getPostId() {
        return postId;
    }

    public void addLine(String sectionName, String subsectionName, PostLine line) {
        if (sectionName == null) return;

        if (!sections.containsKey(sectionName)) {
            sections.put(sectionName, new PostSection(this, sectionName));
        }

        var section = sections.get(sectionName);
        section.addLine(subsectionName, line);
    }

    public Collection<PostSection> getSections() {
        return sections.values();
    }

    public String getPrettyName() {
        return "Post:" + postId;
    }
}
