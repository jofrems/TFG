package com.drpicox.game.ecs;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class EntityIdGenerator {
    private Map<String, Long> numbersByHint = new HashMap<>();
    public String nextEntityId(String hint) {
        // the one below should be the good one (without hint)
        // return UUID.randomUUID().toString();
        // but just for now, and little bit of testing and in memory database....
        var last = numbersByHint.getOrDefault(hint, 0L);
        var current = last + 1;
        numbersByHint.put(hint, current);
        return hint + "-" + current;
    }
}
