package com.tfg.game.components.resources;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ResourcesRepository extends JpaRepository<Resources, String> {
    List<Resources> findAllByGame(Game game);
}
