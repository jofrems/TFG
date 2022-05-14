package com.tfg.game.components.trades;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

@Component
public class TradeController {
    private final TradeRepository tradeRepository;
    private final TradeEntityDataGenerator tradeEntityDataGenerator;


    public TradeController(TradeRepository tradeRepository, TradeEntityDataGenerator tradeEntityDataGenerator) {
        this.tradeRepository = tradeRepository;
        this.tradeEntityDataGenerator = tradeEntityDataGenerator;
    }

    public void create(String entityId, Game game) {
        var component = new Trade(entityId, game);
        tradeRepository.save(component);
    }


}
