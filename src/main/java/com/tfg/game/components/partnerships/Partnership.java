package com.tfg.game.components.partnerships;
import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Partnership extends EcsComponent{
    private String partnership1;
    private String partnership2;

    public Partnership(String entityId, Game game, String p1, String p2){
        super(entityId, game);
        this.partnership1 = p1;
        this.partnership2 = p2;
    }
    protected Partnership(){}

    public String getPartnership1() {
        return this.partnership1;
    }

    public String getPartnership2(){
        return this.partnership2;
    }
}
