package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Empty extends Tile {
    public Empty(){
        super(Tiles.Empty);
        this.isPassable = true;
        this.currentImage = "empty.png";
        this.defaultImage = "empty.png";
    }

    @Override
    public boolean action(Player p) {return isPassable;}
}
