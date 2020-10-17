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
    private String tileset;
    private boolean pickedUp = false;

    public Key(String colour, String tileset){
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

    @Override
    public String getJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("colour", getColour())
                .add("isPassable", getPassable())
                .add("type", getType().toString())
                .add("xLoc", getXLoc())
                .add("yLoc", getYLoc())
                .add("image", getCurrentImage())
                .add("defaultImage", getDefaultImage());

        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(builder.build());
            return writer.toString();
        } catch (IOException e) {
            throw new Error("Error parsing " + this.toString() + " to json");
        }
    }

    @Override
    public Tile jsonToTile(JsonReader json) {
        JsonObject tile = json.readObject();
        colour = tile.getString("colour");
        isPassable = tile.getBoolean("isPassable");
        setXLoc(tile.getInt("xLoc"));
        setYLoc(tile.getInt("yLoc"));
        currentImage = tile.getString("image");
        defaultImage = tile.getString("defaultImage");
        return this;
    }

    //Getters and Setters
    public String getColour(){return colour;}
    public void setColour(String newColour){colour = newColour;}

    public boolean getPickedUp(){return pickedUp;}
    public void setPickedUp(boolean isPickedUp){pickedUp = isPickedUp;}
}
