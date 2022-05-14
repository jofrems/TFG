package com.tfg.game.components.trades;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TradeRepository extends JpaRepository<Trade, String> {
    List<Trade> findAllByGame(Game game);
}
