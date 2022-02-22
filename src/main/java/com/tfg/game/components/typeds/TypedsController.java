package com.tfg.game.components.typeds;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypedsController {

    private final TypedsRepository typedsRepository;

    public TypedsController(TypedsRepository typedsRepository) {
        this.typedsRepository = typedsRepository;
    }

    public void create(String entityId, Game game, String entityType) {
        var component = new Typed(entityId, game, entityType);
        typedsRepository.save(component);
    }

    public boolean isTyped(String entityId, String entityType) {
        return typedsRepository.findById(entityId).map(c -> c.isType(entityType)).orElse(false);
    }

    public List<Typed> findAllByGameAndType(Game game, String type) {
        return typedsRepository.findAllByGameAndType(game, type);
    }

}
