package com.drpicox.game.testPost.helpers;

import com.drpicox.game.testPost.SnapshotService;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;

@Component
public class SaveSnapshots {

    private final SnapshotService snapshotService;

    public SaveSnapshots(SnapshotService snapshotService) {
        this.snapshotService = snapshotService;
    }

    public void save(String postId) {
        var snapshots = snapshotService.listJsonSnapshots();
        var file = getSnapshotFile(postId);
        try (var fw = new FileWriter(file)) {
            fw.append(snapshots);
            fw.flush();
        } catch (Exception reason) {
            throw new RuntimeException("Cannot write post snapshots for " + postId, reason);
        }
    }

    private File getSnapshotFile(String postId) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("snapshots");
        String path = url.getPath().replaceAll("%20", " ");
        return new File(path, postId + ".json");
    }
}
