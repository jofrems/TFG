package com.drpicox.game.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonPath {

    private static final Pattern VALID_PATH = Pattern.compile("(([\\w][\\w\\d_]+)|(\\[[^\\[]\\]))((\\.[^.]+)|(\\[\\d+\\]))*");
    private static final Pattern PATH_PART = Pattern.compile("(\\[\\d+\\])|([^.\\[]+)");

    private String current;
    private JsonPath next;

    public JsonPath(String jsonPath) {
        this(PATH_PART.matcher(jsonPath));

        if (!VALID_PATH.matcher(jsonPath).matches())
            throw new IllegalArgumentException("JsonPath '" + jsonPath + "' is not valid");
    }

    private JsonPath(Matcher matcher) {
        if (!matcher.find()) {
            return;
        }

        this.current = matcher.group();
        this.next = new JsonPath(matcher);
    }

    public boolean isThis() {
        return current == null;
    }

    public boolean isIndex() {
        return !isThis() && current.charAt(0) == '[';
    }

    public boolean isProperty() {
        return !isThis() && current.charAt(0) != '[';
    }

    public String getProperty() {
        return current;
    }

    public int getIndex() {
        return Integer.parseInt(current, 1, current.length() - 1, 10);
    }

    public JsonPath getNext() {
        return next;
    }

    @Override
    public String toString() {
        if (next == null) return "";
        if (!isIndex() && next.isProperty()) return current + "." + next;
        return current + next;
    }
}
