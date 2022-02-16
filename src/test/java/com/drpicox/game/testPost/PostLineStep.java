package com.drpicox.game.testPost;

import com.drpicox.game.testPost.reader.PostLine;

public interface PostLineStep {
    boolean match(PostLine line);
    void run(PostLine line);
    String getPrettyPrint();
}
