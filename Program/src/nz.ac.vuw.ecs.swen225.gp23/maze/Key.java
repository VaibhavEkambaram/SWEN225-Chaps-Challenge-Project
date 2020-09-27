package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
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
        this.image = "key_" + colour + ".png";
    }

    @Override
    public boolean action(Player p) {
       if(!pickedUp){
           p.addItem(this.toString());
           image = "empty.png";
           pickedUp = true;
       }
       return isPassable;
    }

    @Override
    public String toString() {return "key_"+colour;}

    @Override
    public String getJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("colour", getColour())
                .add("isPassable", getPassable())
                .add("type", getType().toString())
                .add("xLoc", getXLoc())
                .add("yLoc", getYLoc())
                .add("image", getImage());

        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(builder.build());
            return writer.toString();
        } catch (IOException e) {
            throw new Error("Error parsing " + this.toString() + " to json");
        }
    }

    //Getters and Setters
    public String getColour(){return colour;}
    public void setColour(String newColour){colour = newColour;}

    public boolean getPickedUp(){return pickedUp;}
    public void setPickedUp(boolean isPickedUp){pickedUp = isPickedUp;}
}
