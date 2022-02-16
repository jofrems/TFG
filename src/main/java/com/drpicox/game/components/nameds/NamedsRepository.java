package com.drpicox.game.components.nameds;

import com.drpicox.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface NamedsRepository extends JpaRepository<Named, String> {
    List<Named> findAllByGame(Game game);
    List<Named> findAllByGameAndName(Game game, String name);
}
