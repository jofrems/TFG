package com.tfg.game.testPost;

import com.tfg.game.testPost.reader.PostLine;

public interface PostLineStep {
    boolean match(PostLine line);
    void run(PostLine line);
    String getPrettyPrint();
}
