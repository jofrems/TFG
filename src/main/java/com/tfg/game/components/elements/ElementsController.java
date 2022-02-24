package com.tfg.game.components.elements;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElementsController {
    private final ElementsRepository elementsRepository;
    private final ElementsEntityDataGenerator elementsEntityDataGenerator;


    public ElementsController(ElementsRepository elementsRepository, ElementsEntityDataGenerator elementsEntityDataGenerator) {
        this.elementsRepository = elementsRepository;
        this.elementsEntityDataGenerator = elementsEntityDataGenerator;
    }

    public void create(String entityId, Game game) {
        var component = new Elements(entityId, game);
        elementsRepository.save(component);
    }
}
