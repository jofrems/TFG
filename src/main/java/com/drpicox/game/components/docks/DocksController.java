package com.drpicox.game.components.docks;

import com.drpicox.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DocksController {

    private final DocksRepository docksRepository;

    public DocksController(DocksRepository docksRepository) {
        this.docksRepository = docksRepository;
    }

    public void create(String entityId, Game game) {
        var component = new Dock(entityId, game);
        docksRepository.save(component);
    }

    public List<Dock> findAllByGame(Game game) {
        return docksRepository.findAllByGame(game);
    }

    public List<Dock> findAllById(List<String> entityIds) {
        return docksRepository.findAllById(entityIds);
    }
}
