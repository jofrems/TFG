package com.drpicox.game.components.owneds;

import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface OwnedsRepository extends JpaRepository<Owned, String> {
    List<Owned> findAllByGame(Game game);
    List<Owned> findAllByGameAndOwner(Game game, Player owner);
}
