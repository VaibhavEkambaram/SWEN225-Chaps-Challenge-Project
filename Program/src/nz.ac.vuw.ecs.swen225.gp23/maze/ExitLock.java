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
        this.image = "exit_lock.png";
    }

    @Override
    public boolean action(Player p) {
        if(chipsNeeded == p.getChips()){
            image = "empty.png";
            isPassable = true;
        }
        return isPassable;
    }

    @Override
    public String toString() {return "exit_lock";}

    @Override
    public String getJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("chipsNeeded", getChipsNeeded())
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

    @Override
    public Tile jsonToTile(JsonReader json) {
        JsonObject tile = json.readObject();
        chipsNeeded = tile.getInt("chipsNeeded");
        isPassable = tile.getBoolean("isPassable");
        setXLoc(tile.getInt("xLoc"));
        setYLoc(tile.getInt("yLoc"));
        image = tile.getString("image");
        return this;
    }

    //Getters and Setters
    public void setChipsNeeded(int numberOfChips){chipsNeeded = numberOfChips;}
    public int getChipsNeeded(){return chipsNeeded;}
}
