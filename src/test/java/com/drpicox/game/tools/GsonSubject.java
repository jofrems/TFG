package com.drpicox.game.tools;

import com.google.common.truth.*;
import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.LinkedList;

import static com.google.common.truth.Truth.assertAbout;

public class GsonSubject extends Subject {

    private JsonElement root;
    private final JsonElement actual;
    private String path = "";

    public GsonSubject(FailureMetadata metadata, JsonElement actual) {
        super(metadata, actual);

        this.root = actual;
        this.actual = actual;
    }

    public static Factory<GsonSubject, JsonElement> json() {
        return GsonSubject::new;
    }

    public static GsonSubject assertThat(String path, JsonElement actual) {
        var result = assertAbout(json()).that(actual);
        result.path = path;
        return result;
    }

    private static GsonSubject assertThat(String path, JsonElement root, JsonElement actual) {
        var result = assertThat(path, actual);
        result.root = root;
        return result;
    }

    public GsonSubject at(String key) {
        var newPath = path + "." + key;

            return assertThat(newPath, root, actual.getAsJsonObject().get(key));

    }

    public GsonSubject at(int index) {
        var newPath = path + "[" + index + "]";

        return assertThat(newPath, root, actual.getAsJsonArray().get(index));
    }

    /*
    public void containsMatch(JsonElement expected) {
        var size = actual.filter(current -> current.matches(expected)).size();
        if (size == 0) {
            failWithRoot(".containsMatch(expected)", Fact.fact("expected", expected));
        }
    }

    public void notContainsMatch(JsonElement expected) {
        var size = actual.filter(current -> current.matches(expected)).size();
        if (size != 0) {
            failWithRoot(".notContainsMatch(expected)", Fact.fact("expected", expected));
        }
    }

    public void matches(JsonElement expected) {
        if (!actual.matches(expected)) {
            failWithRoot("matches(expected)", Fact.fact("expected", expected));
        }
    }
     */

    /*
    public void isNull() {
        actual.
        if (actual == null) return;
        if (actual.isNull()) return;

        failWithRoot(".isNull()", Fact.fact("expected", "null"));
    }

    public void isNotNull() {
        if (actual != null && !actual.isNull()) return;

        failWithRoot(".isNotNull()", Fact.fact("expected", "<not null>"));
    }
    */


    public void isPresent() {
        System.out.println("potato");
    }

    public void isString() {
        if (!actual.isJsonPrimitive())
            failWithRoot(".isString()", Fact.fact("expected", "<to be a string>"));

        if (!actual.getAsJsonPrimitive().isString())
            failWithRoot(".isString()", Fact.fact("expected", "<to be a string>"));
    }


    public IntegerSubject asNumber() {
        if (!actual.isJsonPrimitive())
            failWithRoot(".getAsNumber()", Fact.fact("expected", "<to be a number>"));

        var primitive = actual.getAsJsonPrimitive();
        if (!primitive.isNumber()) {
            failWithRoot(".getAsNumber()", Fact.fact("expected", "<to be a number>"));
        }

        return check(".getAsNumber()").that(primitive.getAsInt());
    }

    public StringSubject asString() {
        if (!actual.isJsonPrimitive())
            failWithRoot(".getAsString()", Fact.fact("expected", "<to be a string>"));

        var primitive = actual.getAsJsonPrimitive();
        if (!primitive.isString()) {
            failWithRoot(".getAsString()", Fact.fact("expected", "<to be a string>"));
        }

        return check(".getAsString()").that(primitive.getAsString());
    }

    protected StandardSubjectBuilder check(String type) {
        return super.check(path + type);
    }

    protected void failWithRoot(String message, Fact first, Fact... rest){
        var facts = new LinkedList<Fact>();

        if (root != actual) {
            facts.add(Fact.fact("given", root));
            facts.add(Fact.fact("path", path));
        }
        facts.add(Fact.fact("actual", actual));

        facts.add(first);
        facts.addAll(Arrays.asList(rest));

        if (path.length() > 0 && !path.startsWith("[")) {
            message = "json" + path + message;
        } else {
            message = "json." + path + message;
        }

        failWithoutActual(Fact.simpleFact(message), facts.toArray(Fact[]::new));
    }
}
