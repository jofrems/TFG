import { PostTest } from "./PostTest";
import { readResource } from "./readResource";

export function readPost(postId: string): PostTest[] {
    var posts:PostTest[] = [];
    
    var postContent = readResource("blog", `${postId}.md`);
    postContent.split('\n').forEach((line, index) => {
        if (line.match(/^##[^#]/)) posts.push(new PostTest(postId, line));
        if (!posts.length) return;
        var lastPost = posts[posts.length - 1];
        lastPost.addLine(line, index+1);
    });

    return posts;
}