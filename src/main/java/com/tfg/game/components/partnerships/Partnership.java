package com.tfg.game.components.partnerships;
import com.tfg.game.ecs.EcsComponent;
import com.tfg.game.games.Game;

import javax.persistence.Entity;

@Entity
public class Partnership extends EcsComponent{
    private String partnership1;
    private String partnership2;
    private String partnership3;

    public Partnership(String entityId, Game game, String p1, String p2){
        super(entityId, game);
        this.partnership1 = p1;
        this.partnership2 = p2;
        this.partnership3 = "";
    }
    public Partnership(String entityId, Game game){
        super(entityId, game);
        this.partnership1 = "";
        this.partnership2 = "";
        this.partnership3 = "";
    }
    protected Partnership(){}

    public String getPartnership1() {
        return this.partnership1;
    }

    public String getPartnership2(){
        return this.partnership2;
    }

    public String getPartnership3(){
        return this.partnership3;
    }


    public void updatePartnership(String partnership){
        if (this.partnership1 == "")
            this.partnership1 = partnership;
        else if (this.partnership2 == "")
            this.partnership2 = partnership;
        else{
            this.partnership3 = partnership;
        }
    }
}
