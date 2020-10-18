package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class ExitLock extends Tile {
    private int chipsNeeded;
    public ExitLock(){
        super(Tiles.ExitLock);
        this.isPassable = false;
        this.currentImage = "exit_lock.png";
        this.defaultImage = "exit_lock.png";
    }

    @Override
    public boolean action(Player p) {
        if(chipsNeeded == p.getChips()){
            this.defaultImage = "floor.png";
            isPassable = true;
        }
        return isPassable;
    }

    //Getters and Setters
    public void setChipsNeeded(int numberOfChips){chipsNeeded = numberOfChips;}
    public int getChipsNeeded(){return chipsNeeded;}
}
