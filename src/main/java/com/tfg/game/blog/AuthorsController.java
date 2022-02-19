package com.tfg.game.blog;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorsController {

    private List<Author> authors;

    public List<Author> findAll() {
        if (authors == null) {
            try {
                authors = loadAuthors();
            } catch (Exception e) {
                throw new RuntimeException("Cannot load authors", e);
            }
        }

        return authors;
    }

    public Optional<Author> findAuthor(String authorAlias) {
        return findAll().stream().filter(a -> a.hasAuthorAlias(authorAlias)).findFirst();
    }

    private List<Author> loadAuthors() throws Exception {
        var result = new LinkedList<Author>();

        var files = getResourceAuthorsFiles();
        for (File file : files) {
            var authorAlias = file.getName().split("\\.")[0];
            var author = loadAuthor(authorAlias, file);
            result.add(author);
        }
        return result;
    }

    private Author loadAuthor(String authorAlias, File file) throws Exception {
        var author = new Author(authorAlias);

        try (
                var fr = new FileReader(file);
                var lr = new LineNumberReader(fr)
        ) {
            var line = lr.readLine();
            while (line != null) {
                if (line.matches("^\\s+$")) ;
                else if (line.matches("^#.*$")) ;
                else if (line.startsWith("email:")) author.addEmail(getValue(line));
                else if (line.startsWith("github:")) author.addGithubUsername(getValue(line));
                else if (line.startsWith("name:")) author.replaceName(getValue(line));
                else throw new IllegalAuthorFileFormatException("Illegal line at " + authorAlias + ".txt:" + lr.getLineNumber() + ": Unexpected content \""+ line +"\"");
                line = lr.readLine();
            }
        }

        author.verify();
        return author;
    }

    private String getValue(String line) {
        var indexOfColon = line.indexOf(':');
        var result = line.substring(indexOfColon + 1).trim();
        return result;
    }

    private File[] getResourceAuthorsFiles() {
        String path = getAuthorsPath();
        return new File(path).listFiles();
    }

    private String getAuthorsPath() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("authors");
        return url.getPath().replaceAll("%20", " ");
    }
}
