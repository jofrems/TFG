package com.drpicox.game.tools;

import com.google.gson.*;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Predicate;

public class JsonOld {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private JsonElement element;

    public JsonOld(String string) {
        this(GSON.fromJson(string, JsonElement.class));
    }
    public JsonOld(JsonElement element) {
        if (element == null) element = JsonNull.INSTANCE;
        this.element = element;
    }

    public JsonElement getElement() {
        return element;
    }

    public JsonOld get(String path) {
        return get(new JsonPath(path));
    }
    private JsonOld get(JsonPath path) {
        var current = element;
        while (!path.isThis() && !current.isJsonNull()) {
            if (path.isIndex()) {
                current = getJsonArrayIndex(current, path);
            } else if (path.isProperty()) {
                current = getJsonObjectProperty(current, path);
            } else {
                throw new IllegalStateException("Illegal jsonPath state");
            }
            path = path.getNext();
        }
        return new JsonOld(current);
    }
    public Integer getAsNumber() {
        if (element.isJsonNull()) return null;
        return element.getAsInt();
    }
    public Integer getAsNumber(String path) {
        return get(path).getAsNumber();
    }
    public String getAsString() {
        if (element.isJsonNull()) return null;
        return element.getAsString();
    }
    public String getAsString(String path) {
        return get(path).getAsString();
    }
    public Object getAsJava(String path) {
        return get(path).getAsJava();
    }
    public Object getAsJava() {
        return getAsJava(element);
    }

    public boolean isNull() {
        return element.isJsonNull();
    }
    public JsonOld filter(Predicate<JsonOld> predicate) {
        JsonArray source = element.getAsJsonArray();
        JsonArray target = new JsonArray();

        source.forEach(item -> {
            if (predicate.test(new JsonOld(item))) {
                target.add(item);
            }
        });

        return new JsonOld(target);
    }
    public JsonOld findMatch(JsonOld expected) {
        var result = filter(j -> j.matches(expected));
        return result.get("[0]");
    }
    public Set<String> keys() {
        return element.getAsJsonObject().keySet();
    }
    public boolean matches(JsonOld expected) {
        return matches(element, expected.element);
    }
    public Integer size() {
        if (!element.isJsonArray()) return null;
        return element.getAsJsonArray().size();
    }

    public JsonOld get(int index) {
        if (!element.isJsonArray()) return this;
        return new JsonOld(element.getAsJsonArray().get(index));
    }
    public JsonOld add(JsonOld value) {
        if (!element.isJsonArray()) return this;
        element.getAsJsonArray().add(value.element);
        return this;
    }

    public JsonOld insert(int index, JsonOld value) {
        if (!element.isJsonArray()) return this;
        var array = element.getAsJsonArray();

        var size = array.size();
        array.add("overwriteme");
        for (var i = size; i > index; i -= 1) {
            array.set(i, array.get(i - 1));
        }
        array.set(index, value.element);
        return this;
    }

    public JsonOld set(String jsonPath, JsonOld value) {
        this.set(jsonPath, value.element);
        return this;
    }
    public JsonOld set(String jsonPath, int number) {
        this.set(jsonPath, new JsonPrimitive(number));
        return this;
    }
    public JsonOld set(String jsonPath, String string) {
        this.set(jsonPath, new JsonPrimitive(string));
        return this;
    }


    private Object getAsJava(JsonElement element) {
        if (element.isJsonPrimitive()) getAsJava(element.getAsJsonPrimitive());
        if (element.isJsonObject()) getAsJava(element.getAsJsonObject());
        if (element.isJsonArray()) getAsJava(element.getAsJsonArray());
        return null;
    }

    private Object getAsJava(JsonPrimitive element) {
        if (element.isNumber()) return element.getAsInt();
        if (element.isString()) return element.getAsString();
        return element.getAsBoolean();
    }

    private Object getAsJava(JsonObject element) {
        var result = new LinkedHashMap<String,Object>();
        for(var key: element.keySet()) {
            result.put(key, getAsJava(element.get(key)));
        }
        return result;
    }

    private Object getAsJAva(JsonArray element) {
        var result = new LinkedList<Object>();
        for (var e: element) {
            result.add(e);
        }
        return result;
    }


    private JsonElement getJsonObjectProperty(JsonElement current, JsonPath path) {
        var property = path.getProperty();
        var object = current.getAsJsonObject();
        if (object.has(property)) {
            current = object.get(property);
        } else {
            current = JsonNull.INSTANCE;
        }
        return current;
    }

    private JsonElement getJsonArrayIndex(JsonElement current, JsonPath path) {
        var index = path.getIndex();
        var array = current.getAsJsonArray();
        if (index < array.size()) {
            current = array.get(index);
        } else {
            current = JsonNull.INSTANCE;
        }
        return current;
    }

    private void set(String path, JsonElement newValue) {
        element = set(element, new JsonPath(path), newValue);
    }

    private JsonElement set(JsonElement element, JsonPath path, JsonElement newValue) {
        if (path.isThis()) return newValue;

        if (path.isIndex()) {
            if (!element.isJsonArray()) {
                element = new JsonArray();
            }
            var child = set(getJsonArrayIndex(element, path), path.getNext(), newValue);
            var index = path.getIndex();
            var array = element.getAsJsonArray();
            while (array.size() <= index) {
                array.add(JsonNull.INSTANCE);
            }
            array.set(index, child);
            return element;
        }

        if (path.isProperty()) {
            if (!element.isJsonObject()) {
                element = new JsonArray();
            }
            var child = set(getJsonObjectProperty(element, path), path.getNext(), newValue);
            element.getAsJsonObject().add(path.getProperty(), child);
            return element;
        }

        throw new IllegalStateException("Illegal jsonPath state");
    }

    @Override
    public String toString() {
        return element.toString();
    }

    private boolean matches(JsonElement source, JsonElement expected) {
        if (source.isJsonNull()) return expected.isJsonNull();
        if (source.isJsonPrimitive()) return matches(source.getAsJsonPrimitive(), expected);
        if (source.isJsonArray()) return matches(source.getAsJsonArray(), expected);
        if (source.isJsonObject()) return matches(source.getAsJsonObject(), expected);
        return false;
    }

    private boolean matches(JsonPrimitive source, JsonElement expected0) {
        if (!expected0.isJsonPrimitive()) return false;
        var expected = expected0.getAsJsonPrimitive();

        if (source.isNumber()) return matches(source.getAsInt(), expected);
        if (source.isString()) return matches(source.getAsString(), expected);
        if (source.isBoolean()) return matches(source.getAsBoolean(), expected);
        throw new IllegalStateException("Uknown source type: " + source);
    }

    private boolean matches(int source, JsonPrimitive expected) {
        if (!expected.isNumber()) return false;

        return source == expected.getAsInt();
    }

    private boolean matches(String source, JsonPrimitive expected) {
        if (!expected.isString()) return false;

        return source.equals(expected.getAsString());
    }

    private boolean matches(boolean source, JsonPrimitive expected) {
        if (!expected.isBoolean()) return false;

        return source == expected.getAsBoolean();
    }

    private boolean matches(JsonArray source, JsonElement expected0) {
        if (!expected0.isJsonArray()) return false;
        var expected = expected0.getAsJsonArray();

        if (source.size() != expected.size()) return false;

        var index = 0;
        for (var sItem : source) {
            var eItem = expected.get(index);
            if (!matches(sItem, eItem)) return false;

            index += 1;
        }

        return true;
    }

    private boolean matches(JsonObject source, JsonElement expected0) {
        if (!expected0.isJsonObject()) return false;
        var expected = expected0.getAsJsonObject();

        for (var key: expected.keySet()) {
            if (!source.has(key)) return false;

            var sItem = source.get(key);
            var eItem = expected.get(key);
            if (!matches(sItem, eItem)) return false;
        }

        return true;
    }


}
