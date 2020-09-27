package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Empty extends Tile {
    public Empty(){
        super(Tiles.Empty);
        this.isPassable = true;
        this.image = "empty.png";
    }

    @Override
    public boolean action(Player p) {return isPassable;}

    @Override
    public String toString() {return "empty";}


    @Override
    public String getJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
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
}
