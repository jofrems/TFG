package com.tfg.game.testSteps.dices;

import com.tfg.game.testSteps.game.EntityResponse;
import com.tfg.game.testSteps.game.EntityTestView;
import com.tfg.game.testSteps.game.GameTestView;
import org.springframework.stereotype.Component;

import static com.tfg.game.testSteps.game.TypedTestView.byType;

@Component
public class DicesTestView {

    private final EntityTestView entityTestView;
    private final GameTestView gameTestView;

    public DicesTestView(EntityTestView entityTestView, GameTestView gameTestView) {
        this.entityTestView = entityTestView;
        this.gameTestView = gameTestView;
    }
    public EntityResponse test(){return gameTestView.streamEntities(byType("dice")).findFirst().get();}
    public int countDices(){return gameTestView.countEntities(byType("dice"));}

    public String getDicesId(){
        return gameTestView.findEntity(byType("dice")).get().getId();
    }



}
