package com.drpicox.game.testSteps.game;

import com.drpicox.game.games.Game;

import java.util.*;
import java.util.stream.Stream;

public class GameResponse {
    private String gameName;
    private String creatorName;
    private String playerName;
    private String token;
    private int roundNumber;
    private Map<String, EntityResponse> entities = new TreeMap<>();

    public GameResponse(Game game, String playerName, String token) {
        this.gameName = game.getGameName();
        this.creatorName = game.getCreator().getPlayerName();
        this.roundNumber = game.getRoundNumber();
        this.playerName = playerName;
        this.token = token;
    }

    public String getGameName() {
        return this.gameName;
    }

    public String getCreatorName() {
        return this.creatorName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public String getToken() {
        return this.token;
    }

    public Stream<EntityResponse> streamEntities() {
        return entities.values().stream();
    }

    public Optional<EntityResponse> findEntityById(String id) {
        return Optional.ofNullable(entities.get(id));
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public EntityResponse getEntityResponse(String id) {
        return entities.get(id);
    }

    private EntityResponse ensureEntityResponse(String id) {
        var entity = entities.get(id);
        if (entity == null) {
            entity = new EntityResponse(id);
            entity.put("id", id);
            entities.put(id, entity);
        }
        return entity;
    }

    public void putEntityProperty(String entityId, String key, Object value) {
        var entity = ensureEntityResponse(entityId);
        entity.put(key, value);
    }
}
