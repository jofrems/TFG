package com.tfg.game.components.owneds;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnedsController {

    private final OwnedsRepository ownedsRepository;
    private final OwnedsEntityDataGenerator ownedsEntityDataGenerator;

    public OwnedsController(OwnedsRepository ownedsRepository, OwnedsEntityDataGenerator ownedsEntityDataGenerator) {
        this.ownedsRepository = ownedsRepository;
        this.ownedsEntityDataGenerator = ownedsEntityDataGenerator;
    }

    public void create(String entityId, Game game, Player owner) {
        var component = new Owned(entityId, game, owner);
        ownedsRepository.save(component);
    }

    public void create(String entityId, Game game) {
        var component = new Owned(entityId, game);
        ownedsRepository.save(component);
    }

    public List<Owned> findAllByGameAndOwner(Game game, Player owner) {
        return ownedsRepository.findAllByGameAndOwner(game, owner);
    }
}
