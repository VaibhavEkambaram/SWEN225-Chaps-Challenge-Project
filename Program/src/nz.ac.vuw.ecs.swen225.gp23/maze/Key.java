package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Key extends Tile {
    private String colour;
    private boolean pickedUp = false;

    public Key(String colour){
        super(Tiles.Key);
        this.isPassable = true;
        this.colour = colour;
        this.currentImage = "key_" + colour + ".png";
        this.defaultImage = "key_" + colour + ".png";
    }

    @Override
    public boolean action(Player p) {
       if(!pickedUp){
           p.addItem(this.toString());
           this.defaultImage = "floor.png";
           pickedUp = true;
       }
       return isPassable;
    }


    //Getters and Setters
    public String getColour(){return colour;}
    public void setColour(String newColour){colour = newColour;}

    public boolean getPickedUp(){return pickedUp;}
    public void setPickedUp(boolean isPickedUp){pickedUp = isPickedUp;}
}
