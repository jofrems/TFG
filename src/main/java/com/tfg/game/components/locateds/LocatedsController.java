package com.tfg.game.components.locateds;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocatedsController {

    private final LocatedsRepository locatedsRepository;

    public LocatedsController(LocatedsRepository locatedsRepository) {
        this.locatedsRepository = locatedsRepository;
    }

    public void create(String entityId, Game game, int row, int column) {
        var component = new Located(entityId, game, row, column);
        locatedsRepository.save(component);
    }

    public List<Located> findAllByGameAndXAndY(Game game, int x, int y){
        return locatedsRepository.findAllByGameAndXAndY(game,x, y);
    }

    /*public int getRow(String entityId) {
        return locatedsRepository.findById(entityId).get().getRow();
    }


    public List<Located> findByGame(int location) {
        return locatedsRepository.findByLocation(location);
    }

    public List<Located> findByLocationOf(String entityId) {
        var location = getLocation(entityId);
        return findByLocation(location);
    }*/
}
