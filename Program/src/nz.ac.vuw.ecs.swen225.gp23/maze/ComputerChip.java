package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class ComputerChip extends Tile {
    private boolean pickedUp = false;

    public ComputerChip(){
        super(Tiles.ComputerChip);
        this.isPassable = true;
        this.currentImage = "computer_chip.png";
        this.defaultImage = "computer_chip.png";
    }

    @Override
    public boolean action(Player p) {
        if(!pickedUp){
            p.pickUpChip();
            this.defaultImage = "floor.png";
            pickedUp = true;
        }
        return isPassable;
    }

    //Getters and setters
    public boolean getPickedUp(){return pickedUp;}
    public void setPickedUp(boolean isPickedUp){pickedUp = isPickedUp;}
}
