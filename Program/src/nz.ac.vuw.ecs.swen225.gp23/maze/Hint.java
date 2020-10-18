package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class Hint extends Tile {
    public Hint(){
        super(Tiles.Hint);
        this.isPassable = true;
        this.currentImage = "hint.png";
        this.defaultImage = "hint.png";
    }

    @Override
    public boolean action(Player p) {return isPassable;}
}
