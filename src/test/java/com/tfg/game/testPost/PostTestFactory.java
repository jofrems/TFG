package com.tfg.game.testPost;

import com.tfg.game.testPost.helpers.CleanupRepositories;
import com.tfg.game.testPost.helpers.SaveSnapshots;
import com.tfg.game.testPost.reader.PostReader;
import com.tfg.game.testPost.reader.PostSection;
import org.junit.jupiter.api.DynamicTest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostTestFactory {

    private final PostReader postReader;
    private final PostRunner postRunner;
    private final CleanupRepositories cleanupRepositories;
    private final SaveSnapshots saveSnapshots;
    private final SnapshotService snapshotService;
    private final List<BeforePostTest> beforePostTests;
    private final List<AfterPostTest> afterPostTests;

    public PostTestFactory(PostReader postReader, PostRunner postRunner, CleanupRepositories cleanupRepositories, SaveSnapshots saveSnapshots, SnapshotService snapshotService, List<BeforePostTest> beforePostTests, List<AfterPostTest> afterPostTests) {
        this.postReader = postReader;
        this.postRunner = postRunner;
        this.cleanupRepositories = cleanupRepositories;
        this.saveSnapshots = saveSnapshots;
        this.snapshotService = snapshotService;
        this.beforePostTests = beforePostTests;
        this.afterPostTests = afterPostTests;
    }

    public List<DynamicTest> createTests(String postId) throws Exception {
        var result = new ArrayList<DynamicTest>();

        try {
            var content = postReader.read(postId);
            for (var section: content.getSections()) {
                result.add(createTest(postId, section));
            }
        } catch (Throwable th) {
            throw new AssertionError("Error creating the tests for the post: " + postId, th);
        }

        if (result.isEmpty())
            throw new AssertionError("No ## Tests found at post " + postId);

        return result;
    }

    private DynamicTest createTest(String postId, PostSection section) {
        return DynamicTest.dynamicTest(section.getPrettyName(), () -> {
            cleanupRepositories.cleanup();
            snapshotService.startTest(postId, section.getSectionName());
            beforePostTests.forEach(b -> b.beforePostTest());

            postRunner.runSection(section);

            afterPostTests.forEach(a -> a.afterPostTest());
            cleanupRepositories.cleanup();
            saveSnapshots.save(postId);
        });
    }
}
