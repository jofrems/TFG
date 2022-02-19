package com.tfg.game.blog;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PostParser {
    private final String postId;
    private String title;
    private final StringBuilder body = new StringBuilder();
    private final Map<String, String> frontMatter = new LinkedHashMap<>();

    private final List<String> lines;
    private int lineIndex;

    public PostParser(String postId, List<String> lines) {
        this.postId = postId;
        this.lines = lines;
    }

    public Post parse() {
        parseFrontMatter();
        skipEmptyLines();
        parseTitle();
        skipEmptyLines();
        int bodyLineNumber = lineIndex + 1;
        parseBody();

        return new Post(postId, frontMatter, title, bodyLineNumber, body.toString());
    }

    private void parseFrontMatter() {
        expectNotEof("the post should not be empty");
        expectPeekStartWith("---", "the first line of the post should begin with the \"---\" of the frontMatter");
        nextLine();
        while (!isPeekStartingWith("---")) {
            parseFrontMatterLine();
            expectNotEof("the frontMatter should end before reaching the end of file, did you miss adding the \"---\" after the frontMatter data?");
        }
        nextLine();
    }

    private void parseFrontMatterLine() {
        var line = peekLine();
        if (line.matches("^(\\s*|#.*)$")) {
             nextLine();
             return;
        }
        if (!line.matches("^[A-Za-z0-9._]+:.*$"))
            fail("the frontMatter content should be \"key: value\"");

        var indexOfColon = line.indexOf(':');
        var key = line.substring(0, indexOfColon);
        var value = line.substring(indexOfColon + 1).trim();
        frontMatter.put(key, value);
        nextLine();
    }

    private void parseTitle() {
        expectNotEof("The title, \"# Your title here\" should be present after the frontMatter, but it does not appears before reaching the end of file");
        var line = peekLine();
        if (!line.startsWith("# "))
            fail("The title, \"# Your title here\" should be the first thing present after the frontMatter, but found \"" + line + "\" instead");

        title = line.substring(1).trim();
        nextLine();
    }

    private void parseBody() {
        while (isNotEof()) {
            body.append(nextLine()).append('\n');
        }
    }

    private void skipEmptyLines() {
        while (isNotEof() && isPeekEmptyLine()) {
            nextLine();
        }
    }

    private boolean isNotEof() {
        return lineIndex < lines.size();
    }

    private boolean isPeekStartingWith(String prefix) {
        return peekLine().startsWith(prefix);
    }

    private boolean isPeekEmptyLine() {
        return peekLine().matches("^\\s*$");
    }

    private String nextLine() {
        var line = peekLine();
        lineIndex += 1;
        return line;
    }

    private String peekLine() {
        return lines.get(lineIndex);
    }

    private void expectNotEof(String message) {
        if (isNotEof()) return;
        fail("Unexpectedly reached end of file, " + message);
    }

    private void expectPeekStartWith(String prefix, String message) {
        if (peekLine().startsWith(prefix)) return;
        fail("The line should begin with \"" + prefix+ "\", " + message);
    }

    private void fail(String message) {
        int lineNumber = lineIndex + 1;
        var details = new StringBuffer();
        details.append("\n  ").append(message).append(".\n");
        details.append("  - ").append(postId).append(".md; line: ").append(lineNumber).append("\n");

        var top = Math.max(0, lineIndex - 3);
        var bottom = Math.min(lines.size(), lineIndex + 3);
        if (top != 0) details.append("       ...\n");
        for (var index = top; index < bottom; index+=1) {
            details.append("    ");
            if (index == lineIndex) details.append("=> "); else details.append("   ");
            details.append(lines.get(index));
            details.append("\n");
        }
        if (bottom != lines.size()) details.append("       ...\n");

        throw new IllegalPostFileFormatException(postId, lineNumber, details.toString());
    }
}
