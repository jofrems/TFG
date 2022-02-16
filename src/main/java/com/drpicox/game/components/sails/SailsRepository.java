package com.drpicox.game.components.sails;

import com.drpicox.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface SailsRepository extends JpaRepository<Sail, String> {
    List<Sail> findAllByGame(Game game);
}
