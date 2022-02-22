package com.tfg.game.components.owneds;

import com.tfg.game.games.Game;
import com.tfg.game.players.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface OwnedsRepository extends JpaRepository<Owned, String> {
    List<Owned> findAllByGame(Game game);
    List<Owned> findAllByGameAndOwner(Game game, Player owner);
}
