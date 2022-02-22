package com.tfg.game.entities.inventory;

import com.tfg.game.games.Game;
import com.tfg.game.games.GameJoiner;
import com.tfg.game.players.Player;
import org.springframework.stereotype.Component;

@Component
public class InventoryGameJoiner implements GameJoiner {

    private final com.tfg.game.entities.inventory.InventoryFactory inventoryFactory;

    public InventoryGameJoiner(com.tfg.game.entities.inventory.InventoryFactory inventoryFactory) {
        this.inventoryFactory = inventoryFactory;
    }

    @Override
    public void joinGame(Player owner, Game game) {
        /*int initialPopulation = 10;
        int initialResources = 10;
        int initialHP = 10;
        int initialResearchPoints = 0;*/

        inventoryFactory.buildInventory(game, owner);
    }
}
