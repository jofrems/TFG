package com.tfg.game.components.elements;

import com.tfg.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ElementsRepository extends JpaRepository<Elements, String> {
    List<Elements> findAllByGame(Game game);
}
