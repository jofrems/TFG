package com.tfg.game.components.tileType;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface TileTypeRepository extends JpaRepository<TileType, String> {
    List<TileType> findAllByGame(Game game);
}
