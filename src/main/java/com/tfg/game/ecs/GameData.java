package com.tfg.game.ecs;

import com.tfg.game.games.Game;

import java.util.Map;
import java.util.TreeMap;

public class GameData {
    private String gameName;
    private String creatorName;
    private int roundNumber;
    private String playerName;
    private String token;
    private Map<String, Map<String, Object>> entities = new TreeMap<>();

    public GameData(Game game, String playerName, String token) {
        this.gameName = game.getGameName();
        this.creatorName = game.getCreator().getPlayerName();
        this.roundNumber = game.getRoundNumber();
        this.playerName = playerName;
        this.token = token;
    }

    public void putEntityProperty(String entityId, String key, Object value) {
        var entity = ensureEntityResponse(entityId);
        entity.put(key, value);
    }

    private Map<String, Object> ensureEntityResponse(String id) {
        var entity = entities.get(id);
        if (entity == null) {
            entity = new TreeMap<>();
            entity.put("id", id);
            entities.put(id, entity);
        }
        return entity;
    }
}
