package com.drpicox.game.testPost.reader;

import java.util.regex.Pattern;

public class PostLine {
    private final String postId;
    private final int line;
    private final String content;

    public PostLine(String postId, int line, String content) {
        this.postId = postId;
        this.line = line;
        this.content = content;
    }

    public boolean isSection() {
        return this.content.matches("^##[^#].*");
    }

    public boolean isSubsection() {
        return this.content.matches("^###[^#].*");
    }

    public String getSlug() {
        return this.content
                .toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+", "")
                .replaceAll("-+$", "");
    }

    public String getPrettyPrint() {
        return postId + ":" + line + " " + content;
    }

    public String[] match(String regex) {
        var pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        var matcher = pattern.matcher(content);
        var isMatch = matcher.find(0);
        if (!isMatch) return null;

        var match = matcher.toMatchResult();
        var groups = new String[match.groupCount() + 1];
        for (var i = 0; i <= match.groupCount(); i++)
            groups[i] = match.group(i);

        return groups;
    }

    public String getContent() {
        return content;
    }

    public boolean startsWith(String prefix) {
        return content.startsWith(prefix);
    }

    public boolean endsWith(String suffix) {
        return content.endsWith(suffix);
    }
}
