package com.drpicox.game;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.drpicox.game.blog.IllegalPostFileFormatException;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import com.drpicox.game.blog.Post;
import com.drpicox.game.blog.PostParser;

public class PostParserTest {

    @Test
    public void parses_successfully_a_blog_post() {
        var post = parse(
                "---",
                "akey: avalue",
                "bkey: bvalue ",
                "---",
                " ",
                "# The title ",
                "",
                "first line  ",
                " second line"
        );

        assertThat(post.getId()).isEqualTo("dummy");
        assertThat(post.getValue("akey")).isEqualTo("avalue");
        assertThat(post.getValue("bkey")).isEqualTo("bvalue");
        assertThat(post.getTitle()).isEqualTo("The title");
        assertThat(post.getBody()).isEqualTo("first line  \n second line\n");
    }

    @Test
    public void front_matter_allows_spaces_and_comments() {
        var post = parse("---", "foo: bar", "    ", "# some comment", "other: value", "---", "# Title", "content");

        assertThat(post.getId()).isEqualTo("dummy");
        assertThat(post.getValue("foo")).isEqualTo("bar");
        assertThat(post.getValue("other")).isEqualTo("value");
        assertThat(post.getKeys()).containsExactly("foo", "other");
        assertThat(post.getTitle()).isEqualTo("Title");
        assertThat(post.getBody()).isEqualTo("content\n");
    }

    @Test void failures_in_parsing() {
        assertThrows(IllegalPostFileFormatException.class, () -> parse());
        assertThrows(IllegalPostFileFormatException.class, () -> parse("no frontmatter", "---", "title:x", "---"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---","foo: no frontmatter end", "other: prop"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---","unexpected frontmatter line", "---", "bla"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---","other:prop", "---", "no title"));
        assertThrows(IllegalPostFileFormatException.class, () -> parse("---","only: frontmatter", "---", ""));
    }

    private static Post parse(String ...lines) {
        var parser = new PostParser("dummy", Arrays.asList(lines));
        return parser.parse();
    }
}
