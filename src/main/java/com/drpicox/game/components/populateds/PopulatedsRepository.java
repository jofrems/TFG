package com.drpicox.game.components.populateds;

import com.drpicox.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PopulatedsRepository extends JpaRepository<Populated, String> {
    List<Populated> findAllByGame(Game game);
}
