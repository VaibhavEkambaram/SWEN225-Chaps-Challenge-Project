package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Wall extends Tile {
    public Wall(){
        super(Tiles.Wall);
        this.isPassable = false;
        this.currentImage = "wall.png";
        this.defaultImage = "wall.png";
    }

    @Override
    public boolean action(Player p) {
        return isPassable;
    }
}
