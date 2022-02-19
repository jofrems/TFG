package com.tfg.game.testSteps.game;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PrettyKey {
    public static String getKey(String prettyPrint) {
        var words = prettyPrint.split("\\s+");
        var tail = Arrays.stream(words).skip(1).map(w -> w.substring(0, 1).toUpperCase() + w.substring(1)).collect(Collectors.joining(""));

        return words[0] + tail;
    }
}
