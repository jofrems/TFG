package com.tfg.game;

import com.tfg.game.blog.AuthorsController;
import com.tfg.game.tools.JsonOld;
import com.google.common.truth.Truth;
import static com.google.common.truth.Truth.assertThat;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.tfg.game.tools.JsonSubject.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BlogTest {

    private final Set<String> validFrontMatterKeys = new LinkedHashSet<>(){{
        add("writer");
        add("coder");
    }};

    @Autowired private MockMvc mockMvc;

    @Autowired private AuthorsController authorsController;

    @Autowired private @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") Gson gson;

    @Test
    public void there_is_a_list_of_posts_service() throws Throwable {
        var result = mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andReturn();

        var json = new JsonOld(result.getResponse().getContentAsString());
        assertThat(json).at("posts").containsMatch(new JsonOld("{" +
                "\"id\": \"2021-08-08_welcome_player\"," +
                "\"title\": \"Welcome Player\"" +
                "}"));
    }

    @Test
    public void posts_are_in_date_order() throws Throwable {
        var result = mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andReturn();

        var list = new JsonOld(result.getResponse().getContentAsString()).get("posts");
        var size = list.size();
        for (var i = 1; i < size; i++) {
            var d0 = list.get(i-0).get("id").toString();
            var d1 = list.get(i-1).get("id").toString();
            Truth.assertThat(d0).isLessThan(d1);
        }
    }

    @Test
    public void read_one_post_by_id() throws Throwable {
        var result = mockMvc.perform(get("/api/v1/posts/2021-08-08_welcome_player"))
                .andExpect(status().isOk())
                .andReturn();

        var match = new JsonOld("{" +
                "\"id\": \"2021-08-08_welcome_player\"," +
                "\"title\": \"Welcome Player\"" +
                "}");

        var json = new JsonOld(result.getResponse().getContentAsString());
        assertThat(json).matches(match);
        assertThat(json).at("body").asString().startsWith("That is just the beginning.");
        assertThat(json).at("body").asString().contains("Welcome to the game.");
    }

    @Test
    public void all_posts_ids_satisfies_the_format_naming() throws Throwable {
        forEachPost(post -> {
            var id = post.get("id").toString();
            if (id.matches("^20\\d\\d-[01]\\d-[0123]\\d(_[a-z]+)+$")) return;

            throw new AssertionError("Post '" + id + ".md' name should satisfy the naming convention\n" +
                    " - post id: " + id + "\n" +
                    " - it has the date in the beginning as YYYY-MM-DD\n" +
                    " - date is separated by dashes, '-'\n" +
                    " - the text includes only lower case letters with no accents\n" +
                    " - there is one single underscore separating date from text\n" +
                    " - there is one single underscore separating text words\n"
            );

        });
    }

    @Test
    public void all_posts_frontmatter_contains_the_writer() throws Throwable {
        forEachPost(post -> {
            var id = post.get("id").toString();
            var frontMatter = (Map) post.get("frontMatter");

            if (frontMatter.containsKey("writer")) return;

            throw new AssertionError("Post '" + id + ".md' should have a 'writer' in the frontMatter\n" +
                    "post id        : " + id + "\n" +
                    "actual keys are: " + frontMatter.keySet() + "\n" +
                    "expected key is: writer\n"
            );
        });
    }

    @Test
    public void all_posts_frontmatter_contains_only_valid_keys() throws Throwable {
        forEachPost(post -> {
            var id = post.get("id").toString();
            var frontMatter = (Map) post.get("frontMatter");
            var extraneousKeys = new LinkedHashSet<>(frontMatter.keySet());
            extraneousKeys.removeAll(validFrontMatterKeys);

            if (extraneousKeys.size() > 0) {
                throw new AssertionError("Post '" + id + ".md' frontMatter has more than expected keys.\n" +
                        "post id            : " + id + "\n" +
                        "expected keys are  : " + validFrontMatterKeys + "\n" +
                        "actual keys are    : " + frontMatter.keySet() + "\n" +
                        "extraneous keys are: " + extraneousKeys + "\n"
                );
            }
        });
    }

    @Test
    public void all_posts_frontmatter_coder_and_writer_include_only_valid_authors() throws Throwable {
        forEachPost(post -> {
            var id = post.get("id").toString();
            var frontMatter = (Map) post.get("frontMatter");
            validateFrontMatterAuthor(id, frontMatter, "coder");
            validateFrontMatterAuthor(id, frontMatter, "writer");
        });
    }

    private void validateFrontMatterAuthor(String id, Map frontMatter, String role) {
        var authorAlias = (String) frontMatter.get(role);
        if (authorAlias == null) return;

        var validAuthors = authorsController.findAll().stream().map(a -> a.getAuthorAlias()).collect(Collectors.toSet());
        if (validAuthors.contains(authorAlias)) return;

        throw new AssertionError("Post '" + id + ".md' frontMatter key '" + role + "' should have an actual author .\n" +
                "post id                 : " + id + "\n" +
                "frontMatter key         : " + role + "\n" +
                "actual frontMatter value: " + authorAlias + "\n" +
                "expected author aliases : " + validAuthors + "\n"
        );

    }

    @Test
    public void posts_with_coder_should_have_a_test_class_satisfying_the_expected_naming() throws Throwable {
        forEachPost(post -> {
            var id = post.get("id").toString();
            var frontMatter = (Map) post.get("frontMatter");
            if (!frontMatter.containsKey("coder")) return;

            var testClassNameBuilder = new StringBuilder();
            testClassNameBuilder.append("com.tfg.game.Post_");
            testClassNameBuilder.append(id.substring(0,10).replaceAll("-", ""));
            testClassNameBuilder.append("_");
            Arrays.stream(id.toLowerCase().substring(11).split("_")).forEach(word -> {
                testClassNameBuilder.append(word.substring(0,1).toUpperCase());
                testClassNameBuilder.append(word.substring(1));
            });
            testClassNameBuilder.append("_Test");
            var testClassName = testClassNameBuilder.toString();

            try {
                Class.forName(testClassName);
            } catch (ClassNotFoundException e) {
                throw new AssertionError("Post '" + id + ".md' has a coder but test class not found.\n" +
                        "post id            : " + id + "\n" +
                        "expected test class: " + testClassName + "\n" +
                        "- if the class exists, check the naming\n"
                );
            }
        });
    }

    @Test
    public void all_posts_ids_text_should_match_the_title() throws Throwable {
        forEachPost(post -> {
            var id = post.get("id").toString();
            var title = post.get("title").toString();

            var expectedText = title.toLowerCase()
                    .replaceAll("[^a-z]", " ")
                    .replaceAll("\\s+", " ")
                    .trim()
                    .replaceAll("\\s", "_");

            var date = id.substring(0, 10);
            var text = id.substring(11);
            if (text.equals(expectedText)) return;

            throw new AssertionError("Post '" + id + ".md' has a coder but test not found.\n" +
                    "post id    : " + id + "\n" +
                    "post title : " + title + "\n" +
                    "actual id  : " + id + "\n" +
                    "expected id: " + date + "_" + expectedText + "\n"
            );
        });
    }

    @Test
    public void post_first_line_number_should_be_greater_than_one_because_front_matter() throws Throwable {
        forEachPost(post -> {
            var id = post.get("id");
            var bodyLineNumber = (double) post.get("bodyLineNumber");

            if (bodyLineNumber < 2)
                throw new AssertionError("Post '" + id + "'.md bodyLineNumber (" + bodyLineNumber + ") must be greater than 2");
        });
    }

    @Test
    public void posts_cannot_ask_to_print_the_last_snapshot() throws Throwable {
        var ids = new LinkedList();
        forEachPost(postMetadata -> ids.add(postMetadata.get("id")));

        for (var id: ids) {
            var post = fetchPost(id);
            var body = (String) post.get("body");
            var containsPrintLastSnapshot = body.toUpperCase().lines().anyMatch(line -> line.contains("PRINT LAST SNAPSHOT"));
            if (!containsPrintLastSnapshot) return;

            throw new AssertionError("Post '" + id + "'.md body cannot contain a line with the text 'PRINT LAST SNAPSHOT'");
        }
    }

    private void forEachPost(Consumer<Map> consumer) throws Throwable {
        var result = mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andReturn();

        var jsonResponse = result.getResponse().getContentAsString();
        var response = gson.fromJson(jsonResponse, Map.class);
        var posts = (List) response.get("posts");

        for (var i = 0; i < posts.size(); i++) {
            var post = (Map) posts.get(i);
            consumer.accept(post);
        }
    }

    private Map fetchPost(Object postId) throws Throwable {
        var result = mockMvc.perform(get("/api/v1/posts/" + postId))
                .andExpect(status().isOk())
                .andReturn();

        var jsonResponse = result.getResponse().getContentAsString();
        var response = gson.fromJson(jsonResponse, Map.class);
        return response;
    }
}
