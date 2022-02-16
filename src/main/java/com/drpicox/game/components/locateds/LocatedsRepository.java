package com.drpicox.game.components.locateds;

import com.drpicox.game.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.TreeSet;

interface LocatedsRepository extends JpaRepository<Located, String> {
    List<Located> findAllByGame(Game game);

    List<Located> findByGameAndLocation(Game game, int location);

    List<Located> findAllByLocationIn(TreeSet<Integer> locations);
}
