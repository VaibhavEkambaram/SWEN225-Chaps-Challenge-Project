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

    @Override
    public String getJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("chipsNeeded", getChipsNeeded())
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
        chipsNeeded = tile.getInt("chipsNeeded");
        isPassable = tile.getBoolean("isPassable");
        setXLoc(tile.getInt("xLoc"));
        setYLoc(tile.getInt("yLoc"));
        currentImage = tile.getString("image");
        defaultImage = tile.getString("defaultImage");
        return this;
    }

    //Getters and Setters
    public void setChipsNeeded(int numberOfChips){chipsNeeded = numberOfChips;}
    public int getChipsNeeded(){return chipsNeeded;}
}
