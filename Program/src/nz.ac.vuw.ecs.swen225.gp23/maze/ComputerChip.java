package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class ComputerChip extends Tile {
    private boolean pickedUp = false;

    public ComputerChip(){
        super(Tiles.ComputerChip);
        this.isPassable = true;
        this.image = "computer_chip.png";
    }

    @Override
    public boolean action(Player p) {
        if(!pickedUp){
            p.pickUpChip();
            image = "empty.png";
            pickedUp = true;
        }
        return isPassable;
    }

    @Override
    public String toString() {return "computer_chip";}

    @Override
    public String getJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("isPassable", getPassable())
                .add("type", getType().toString())
                .add("xLoc", getXLoc())
                .add("yLoc", getYLoc())
                .add("image", getImage())
                .add("pickedUp", getPickedUp());

        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(builder.build());
            return writer.toString();
        } catch (IOException e) {
            throw new Error("Error parsing " + this.toString() + " to json");
        }
    }

    //Getters and setters
    public boolean getPickedUp(){return pickedUp;}
    public void setPickedUp(boolean isPickedUp){pickedUp = isPickedUp;}
}
