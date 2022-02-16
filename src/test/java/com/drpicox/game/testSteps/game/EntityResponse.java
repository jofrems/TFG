package com.drpicox.game.testSteps.game;

import com.google.gson.annotations.JsonAdapter;

import java.util.Map;
import java.util.TreeMap;

@JsonAdapter(EntityResponseDeserializerSerializer.class)
public class EntityResponse {

    // Internal representation

    private Map<String, Object> properties = new TreeMap<>();

    Map<String, Object> getProperties() {
        return this.properties;
    }

    EntityResponse(Map<String, Object> properties) {
        this.properties = properties;
    }

    // Object

    public EntityResponse(String id) {
        this.properties.put("id", id);
    }

    // Map

    public void put(String key, Object value) {
        properties.put(key, value);
    }

    // Sugar syntax

    public Object getOrDefault(String key, Object defaultValue) {
        return properties.getOrDefault(key, defaultValue);
    }

    public String getId() {
        return (String) properties.get("id");
    }

    public int getInt(String key) {
        return get(key, Double.class).intValue();
    }

    public Object get(String key) {
        return properties.get(key);
    }

    public <T> T get(String key, Class<T> as) {
        if (!properties.containsKey(key))
            throwErrorInEntity("expected to have the property '" + key + "', but it was not present", key);
        return (T) properties.get(key);
    }

    private void throwErrorInEntity(String message, String expectedKey) {
        var builder = new StringBuilder();
        builder.append("The entity '");
        builder.append(properties.get("id"));
        builder.append("' ");
        builder.append(message);
        builder.append(".\nThe entity contents are:\n");

        var maxSpace = new StringBuilder();
        properties.keySet().forEach(key -> {
            while (maxSpace.length() < key.length())
                maxSpace.append(" ");
        });

        if (!properties.containsKey(expectedKey))
            builder.append(" * ").append(expectedKey).append(" (is missing)\n");

        properties.forEach((key, value) -> {
            if (key.equals(expectedKey)) builder.append(" * "); else builder.append(" - ");
            builder.append((key + maxSpace).substring(0, maxSpace.length()));
            builder.append(": '");
            builder.append(value);
            builder.append("' (");
            builder.append(value.getClass().getSimpleName());
            builder.append(")\n");
        });

        throw new AssertionError(builder.toString());
    }

    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }
}
