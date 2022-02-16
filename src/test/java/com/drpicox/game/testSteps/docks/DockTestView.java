package com.drpicox.game.testSteps.docks;

import com.drpicox.game.testSteps.game.EntityResponse;
import com.drpicox.game.testSteps.locateds.LocatedTestView;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class DockTestView {
    public static Predicate<EntityResponse> isDock() {
        return e -> (Boolean) e.getOrDefault("isDock", Boolean.FALSE);
    }

    private final LocatedTestView locatedTestView;

    public DockTestView(LocatedTestView locatedTestView) {
        this.locatedTestView = locatedTestView;
    }

    public String getCoLocatedDockId() {
        return findCoLocatedDockId().get();
    }

    public Optional<String> findCoLocatedDockId() {
        return locatedTestView.streamCoLocateds().filter(isDock()).findAny().map(e -> e.getId());
    }
}
