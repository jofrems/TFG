package com.tfg.game.testPost.helpers;

import com.tfg.game.testPost.SnapshotService;
import com.tfg.game.testPost.SystemPostLineStep;
import com.tfg.game.testPost.reader.PostLine;
import org.springframework.stereotype.Component;

@Component
public class SystemSnapshotStep implements SystemPostLineStep {

    private static final String RE = "SNAPSHOT status=(\\d+)";
    private final SnapshotService snapshotService;

    public SystemSnapshotStep(SnapshotService SnapshotService) {
        this.snapshotService = SnapshotService;
    }

    @Override
    public boolean match(PostLine line) {
        return line.match(RE) != null;
    }

    @Override
    public void run(PostLine line) {
        var match = line.match(RE);
        var status = Integer.parseInt(match[1]);
        snapshotService.expectStatusAndSave(status);
    }

    @Override
    public String getPrettyPrint() {
        return "Deal with a previous snapshot (" + getClass().getName() + ")";
    }
}
