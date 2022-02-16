package com.drpicox.game.tools;

import com.google.common.truth.*;

import java.util.Arrays;
import java.util.LinkedList;

import static com.google.common.truth.Truth.assertAbout;

public class JsonSubject extends Subject {

    private JsonOld root;
    private final JsonOld actual;
    private String path = "";

    public JsonSubject(FailureMetadata metadata, JsonOld actual) {
        super(metadata, actual);

        this.root = actual;
        this.actual = actual;
    }

    public static Factory<JsonSubject, JsonOld> json() {
        return JsonSubject::new;
    }

    public static JsonSubject assertThat(JsonOld actual) {
        return assertAbout(json()).that(actual);
    }

    public JsonSubject at(String subPath) {
        var newPath = path;
        if (path.length() > 0 && !path.endsWith("]") && !subPath.startsWith("[")) {
            newPath += ".";
        }
        newPath += subPath;

        var result = assertThat(actual.get(subPath));
        result.root = root;
        result.path = newPath;
        return result;
    }

    public void containsMatch(JsonOld expected) {
        var size = actual.filter(current -> current.matches(expected)).size();
        if (size == 0) {
            failWithRoot(".containsMatch(expected)", Fact.fact("expected", expected));
        }
    }

    public void notContainsMatch(JsonOld expected) {
        var size = actual.filter(current -> current.matches(expected)).size();
        if (size != 0) {
            failWithRoot(".notContainsMatch(expected)", Fact.fact("expected", expected));
        }
    }

    public void matches(JsonOld expected) {
        if (!actual.matches(expected)) {
            failWithRoot("matches(expected)", Fact.fact("expected", expected));
        }
    }

    public void isNull() {
        if (actual == null) return;
        if (actual.isNull()) return;

        failWithRoot(".isNull()", Fact.fact("expected", "null"));
    }

    public void isNotNull() {
        if (actual != null && !actual.isNull()) return;

        failWithRoot(".isNotNull()", Fact.fact("expected", "<not null>"));
    }

    public void isString() {
        if (actual == null)
            failWithRoot(".isString()", Fact.fact("expected", "<a non null string>"));

        var element = actual.getElement();
        if (!element.isJsonPrimitive())
            failWithRoot(".isString()", Fact.fact("expected", "<to be a string>"));

        if (!element.getAsJsonPrimitive().isString())
            failWithRoot(".isString()", Fact.fact("expected", "<to be a string>"));
    }


    public IntegerSubject asNumber() {
        if (!actual.getElement().isJsonPrimitive())
            failWithRoot(".getAsNumber()", Fact.fact("expected", "<to be a number>"));

        var primitive = actual.getElement().getAsJsonPrimitive();
        if (!primitive.isNumber()) {
            failWithRoot(".getAsNumber()", Fact.fact("expected", "<to be a number>"));
        }

        return check(".getAsNumber()").that(primitive.getAsInt());
    }

    public StringSubject asString() {
        if (!actual.getElement().isJsonPrimitive())
            failWithRoot(".getAsString()", Fact.fact("expected", "<to be a string>"));

        var primitive = actual.getElement().getAsJsonPrimitive();
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
