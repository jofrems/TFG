package com.drpicox.game.blog;

import java.util.LinkedHashSet;
import java.util.Set;

public class Author {
    private String authorAlias;
    private String name;
    private Set<String> githubUsernames = new LinkedHashSet<>();
    private Set<String> emails = new LinkedHashSet<>();

    public Author(String authorAlias) {
        this.authorAlias = authorAlias;
    }

    public boolean hasAuthorAlias(String authorAlias) {
        return this.authorAlias.equals(authorAlias);
    }

    public String getAuthorAlias() {
        return authorAlias;
    }

    public String getName() {
        return name;
    }

    public Set<String> getGithubUsernames() {
        return githubUsernames;
    }

    public Set<String> getEmails() {
        return emails;
    }

    public void addGithubUsername(String githubUsername) {
        githubUsernames.add(githubUsername);
    }

    public void addEmail(String email) {
        emails.add(email);
    }

    public void replaceName(String name) {
        this.name = name;
    }

    public void verify() {
        if (name == null) throw new IllegalAuthorFileFormatException(authorAlias + ".txt: should have a name");
        if (githubUsernames.isEmpty()) throw new IllegalAuthorFileFormatException(authorAlias + ".txt: has no github usernames");
        if (emails.isEmpty()) throw new IllegalAuthorFileFormatException(authorAlias + ".txt: has no emails ");
    }
}
