package com.drpicox.game.components.loadables;

import com.drpicox.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface LoadablesRepository extends JpaRepository<Loadable, String> {
    List<Loadable> findAllByGame(Game game);
}
