package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.JsonReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Tile {
    public boolean isPassable;
    public int yLoc;
    public int xLoc;
    public String image; //for imageURL
    public Tiles type;
    public List<Tile> adjacentTiles = new ArrayList<>();

    //Possible types of tiles
    public enum Tiles {
        Empty, Floor, Wall, LockedDoor, Key, Exit, ExitLock, ComputerChip, Hint
    }

    //Possible movement directions
    public enum Directions {
        Left, Right, Up, Down
    }

    public Tile(Tiles t){
        this.type = t;
    }

    //Abstract methods
    public abstract boolean action(Player p); //validates whether a player can complete the requested action
    public abstract String toString();
    public abstract String getJson(); //generates json string for a given tile
    public abstract Tile jsonToTile(JsonReader json); //sets properties for a tile based on json input

    //Getters and setters
    public int getXLoc(){return this.xLoc;}
    public void setXLoc(int xLoc){
        this.xLoc = xLoc;
    }

    public int getYLoc(){return this.yLoc;}
    public void setYLoc(int yLoc){
        this.yLoc = yLoc;
    }

    public boolean getPassable(){return this.isPassable;}
    public void setPassable(boolean isPassable){
        this.isPassable = isPassable;
    }

    public String getImage(){return this.image;}
    public void setImage(String imageURL){
        this.image = imageURL;
    }

    public Tiles getType(){return type;}
    public void setType(Tiles newType){this.type = newType;}
}
