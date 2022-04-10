package com.tfg.game.components.upgradeds;

import com.tfg.game.components.owneds.Owned;
import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpgradedsController {

    private final UpgradedsRepository upgradedsRepository;

    public UpgradedsController(UpgradedsRepository upgradedsRepository) {
        this.upgradedsRepository = upgradedsRepository;
    }

    public void create(String entityId, Game game) {
        var component = new Upgraded(entityId, game);
        upgradedsRepository.save(component);
    }

    public List<Upgraded> findAllByGame(Game game) {
        return upgradedsRepository.findAllByGame(game);
    }

    public Upgraded findById(String entityId) {
        return upgradedsRepository.findById(entityId).get();
    }

    public Upgraded upgrade(String entityId, boolean shouldUpgrade) {
        var upgraded = upgradedsRepository.findById(entityId).get();
        if(shouldUpgrade){
            upgraded.upgrade();
            upgradedsRepository.save(upgraded);
        }

        return upgraded;
    }

}
