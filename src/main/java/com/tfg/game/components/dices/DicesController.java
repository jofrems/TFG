package com.tfg.game.components.dices;

import com.tfg.game.games.Game;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DicesController {
    private final DicesRepository dicesRepository;
    private final DicesEntityDataGenerator dicesEntityDataGenerator;


    public DicesController(DicesRepository dicesRepository, DicesEntityDataGenerator dicesEntityDataGenerator) {
        this.dicesRepository = dicesRepository;
        this.dicesEntityDataGenerator = dicesEntityDataGenerator;
    }

    public void create(String entityId, Game game) {
        var component = new Dices(entityId, game);
        dicesRepository.save(component);
    }

    public Dices rollDices(String entityId){
        var dices = dicesRepository.findById(entityId).get();

        dices.rollDices();
        dicesRepository.save(dices);

        return dices;
    }

    public Dices getDices(String entityId){
        var dices = dicesRepository.findById(entityId).get();

        return dices;
    }

    public int getDice1(String entityId){
        var dices = dicesRepository.findById(entityId).get();
        return dices.getDice1();
    }

    public int getDice2(String entityId){
        var dices = dicesRepository.findById(entityId).get();
        return dices.getDice2();
    }

    public int getSumDices(String entityId){
        var dices = dicesRepository.findById(entityId).get();

        return dices.getSumDices();
    }
    public Optional<Dices> test(){
        var dices = dicesRepository.findAll().stream().findFirst();
        return dices;
    }
}
