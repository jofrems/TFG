package com.drpicox.game.testSteps.locateds;

import com.drpicox.game.testSteps.game.EntityResponse;
import com.drpicox.game.testSteps.game.EntityTestView;
import com.drpicox.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class LocatedTestView {

    public static Predicate<EntityResponse> byLocation(int location) {
        return e -> e.containsKey("location") && e.getInt("location") == location;
    }

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;

    public LocatedTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }

    public int getLocation() {
        return entityTestView.getEntityPropertyInt("location");
    }

    public Predicate<EntityResponse> byCoLocation() {
        return e -> {
            if (!e.containsKey("location")) return false;
            var myLocation = getLocation();
            var location = e.getInt("location");
            return myLocation == location;
        };
    }

    public Stream<EntityResponse> streamCoLocateds() {
        return gameTestView.streamEntities(byCoLocation());
    }
}
