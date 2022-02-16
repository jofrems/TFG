package com.drpicox.game.blog;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.*;

@Component
public class BlogController {

    private List<Post> posts;

    public List<Post> findAll() {
        if (posts == null) {
            try {
                posts = loadAll();
            } catch (Exception e) {
                throw new RuntimeException("An exception found while loading all posts", e);
            }
        }

        return posts;
    }

    public Optional<Post> findPost(String postId) {
        return findAll().stream().filter(p -> p.getId().equals(postId)).findFirst();
    }

    private List<Post> loadAll() throws Exception {
        var result = new LinkedList<Post>();

        var files = getResourceBlogFiles();
        for (File f : files) {
            var id = f.getName().split("\\.")[0];
            result.add(loadPost(id, f));
        }
        Collections.sort(result);
        return result;
    }

    public Post loadPost(String postId, File file) throws Exception {
        try (
                var fr = new FileReader(file);
                var lr = new LineNumberReader(fr);
        ) {
            var lines = new ArrayList<String>();
            var line = lr.readLine();
            while (line != null) {
                lines.add(line);
                line = lr.readLine();
            }

            return new PostParser(postId, lines).parse();
        }
    }

    private File[] getResourceBlogFiles() {
        String path = getBlogPath();
        return new File(path).listFiles();
    }

    private String getBlogPath() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("blog");
        return url.getPath().replaceAll("%20", " ");
    }
}
