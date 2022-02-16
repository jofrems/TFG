package com.drpicox.game.testPost.helpers;

import com.drpicox.game.testPost.SnapshotService;
import com.drpicox.game.testPost.SystemPostLineStep;
import com.drpicox.game.testPost.reader.PostLine;
import org.springframework.stereotype.Component;

@Component
public class SystemPrintLastSnapshotStep implements SystemPostLineStep {

    private static final String RE = "PRINT LAST SNAPSHOT";
    private final SnapshotService snapshotService;

    public SystemPrintLastSnapshotStep(SnapshotService SnapshotService) {
        this.snapshotService = SnapshotService;
    }

    @Override
    public boolean match(PostLine line) {
        return line.match(RE) != null;
    }

    @Override
    public void run(PostLine line) {
        snapshotService.printLastSnapshot();
    }

    @Override
    public String getPrettyPrint() {
        return "Print with a previous snapshot (" + getClass().getName() + ")";
    }
}
