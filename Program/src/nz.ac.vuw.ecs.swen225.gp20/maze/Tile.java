package nz.ac.vuw.ecs.swen225.gp20.maze;

import java.util.ArrayList;
import java.util.List;

public abstract class Tile {
    public boolean isPassable;
    public int yLoc;
    public int xLoc;
    public String image; //for imageURL
    public Tiles type;

    public enum Tiles {
        Empty, Wall, LockedDoor, Key, Exit, ExitLock, Treasure, Info
    }

    public Tile(Tiles t){
        this.type = t;
    }

    public void setXLoc(int xLoc){
        this.xLoc = xLoc;
    }

    public void setYLoc(int yLoc){
        this.yLoc = yLoc;
    }

    public void setPassable(boolean isPassable){
        this.isPassable = isPassable;
    }

    public void setImage(String imageURL){
        this.image = imageURL;
    }

    // Checks when a player makes an action with the tile that the action is valid
    public abstract boolean action(Player p);
}
