package com.drpicox.game.testPost.reader;

import com.drpicox.game.blog.BlogController;
import com.drpicox.game.blog.Post;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PostReader {

    private final BlogController blogController;

    public PostReader(BlogController blogController) {
        this.blogController = blogController;
    }

    public PostContent read(String postId) {
        var result = new PostContent(postId);

        var lines = readPostLines(postId);
        var currentSection = (String) null;
        var currentSubsection = (String) null;
        for (var line: lines) {
            if (line.isSection()) {
                currentSection = currentSubsection = line.getSlug();
            } else if (line.isSubsection()) {
                currentSubsection = line.getSlug();
            }

            result.addLine(currentSection, currentSubsection, line);
        }

        return result;
    }

    private List<PostLine> readPostLines(String postId) {
        var post = readPost(postId);
        var bodyLineNumber = post.getBodyLineNumber();
        var body = post.getBody();
        String[] lines = body.split("\\r?\\n");

        var result = new LinkedList<PostLine>();
        for (var i = 0; i < lines.length; i++) {
            result.add(new PostLine(postId, i+bodyLineNumber, lines[i]));
        }

        return result;
    }

    private Post readPost(String postId) {
        try {
            return blogController.findPost(postId).orElseThrow(
                    () -> new AssertionError("Post \"" + postId + "\" does not exists")
            );
        } catch (Exception e) {
            throw new AssertionError("Cannot read postId " + postId, e);
        }
    }
}
