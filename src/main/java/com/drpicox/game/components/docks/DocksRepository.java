package com.drpicox.game.components.docks;

import com.drpicox.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface DocksRepository extends JpaRepository<Dock, String> {
    List<Dock> findAllByGame(Game game);
}
