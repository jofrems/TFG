package com.drpicox.game.components.nameds;

import com.drpicox.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NamedsController {

    private final NamedsRepository namedsRepository;

    public NamedsController(NamedsRepository namedsRepository) {
        this.namedsRepository = namedsRepository;
    }

    public void create(String entityId, Game game, String initialName) {
        var component = new Named(entityId, game, initialName);
        namedsRepository.save(component);
    }

    public Named changeName(String entityId, String newName) {
        var named = namedsRepository.findById(entityId).get();
        named.changeName(newName);
        namedsRepository.save(named);
        return named;
    }

    public List<Named> findAllByGameAndName(Game game, String name) {
        return namedsRepository.findAllByGameAndName(game, name);
    }
}
