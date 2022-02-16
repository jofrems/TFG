package com.drpicox.game.components.growingsPopulation;

import com.drpicox.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface GrowingsPopulationsRepository extends JpaRepository<GrowingPopulation, String> {
    List<GrowingPopulation> findAllByGame(Game game);
}
