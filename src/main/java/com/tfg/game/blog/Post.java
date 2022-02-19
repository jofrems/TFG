package com.tfg.game.blog;

import java.util.Map;
import java.util.Set;

public class Post implements Comparable<Post> {

    private final String id;
    private final String title;
    private final int bodyLineNumber;
    private final String body;
    private final Map<String, String> frontMatter;

    public Post(String postId, Map<String, String> frontMatter, String title, int bodyLineNumber, String body) {
        this.id = postId;
        this.frontMatter = frontMatter;
        this.title = title;
        this.bodyLineNumber = bodyLineNumber;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getValue(String key) {
        return frontMatter.get(key);
    }

    public Map<String, String> getFrontMatter() {
        return frontMatter;
    }

    public Set<String> getKeys() {
        return frontMatter.keySet();
    }

    @Override
    public int compareTo(Post o) {
        return -id.compareTo(o.id);
    }

    public int getBodyLineNumber() {
        return this.bodyLineNumber;
    }
}
