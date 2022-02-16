package com.drpicox.game.testSteps.game;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.TreeMap;

public class EntityResponseDeserializerSerializer implements JsonDeserializer<EntityResponse>, JsonSerializer<EntityResponse> {

    @Override
    public EntityResponse deserialize(
            JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        var jsonObject = json.getAsJsonObject();
        var map = (TreeMap<String, Object>) context.deserialize(jsonObject, TreeMap.class);
        return new EntityResponse(map);
    }

    @Override
    public JsonElement serialize(EntityResponse object, Type type, JsonSerializationContext jsonSerializationContext) {
        var properties = object.getProperties();
        return jsonSerializationContext.serialize(properties, properties.getClass());
    }
}
