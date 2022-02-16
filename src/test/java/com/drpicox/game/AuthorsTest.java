package com.drpicox.game;

import com.drpicox.game.blog.AuthorsController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorsTest {

    @Autowired private AuthorsController authorsController;

    @Test
    public void there_should_be_drpicox_author() {
        var drpicox = authorsController.findAuthor("drpicox").orElseThrow();

        assertThat(drpicox.getName()).isEqualTo("David Rodenas Pico");
        assertThat(drpicox.getGithubUsernames()).containsExactly("drpicox");
        assertThat(drpicox.getEmails()).contains("david.rodenas+github@gmail.com");
    }

    @Test
    public void there_should_be_at_least_two_authors() {
        var authors = authorsController.findAll();
        var aliases = authors.stream().map(a -> a.getAuthorAlias());

        assertThat(aliases).containsAtLeast("drpicox", "nobody");
    }

}
