package com.tfg.game.components.tileWeight;

import com.tfg.game.components.elements.Elements;
import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TileWeightRepository extends JpaRepository<TileWeight, String> {
    List<TileWeight> findAllByGame(Game game);
}
