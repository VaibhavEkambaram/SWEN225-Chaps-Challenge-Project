package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.JsonReader;
import java.util.ArrayList;
import java.util.List;

public abstract class Tile {
    public boolean isPassable;
    public boolean hasEntity; //for monsters later
    public int yLoc;
    public int xLoc;
    public String currentImage; //for imageURL
    public String defaultImage;
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

    public void setEntityPresent(String image){
        this.currentImage = image;
    }
    public void setEntityAbsent(){
        this.currentImage = this.defaultImage;
    }

    public String toString(){
        return currentImage.substring(0, currentImage.length()-4);
    }

    //Abstract methods
    public abstract boolean action(Player p); //validates whether a player can complete the requested action

    //Getters and setters
    public Tile getDirection(Directions d){ //gets the adjacent tile in a given direction
        if (adjacentTiles.size() < 4) {
            return null;
        } else {
            return adjacentTiles.get(d.ordinal());
        }
    }

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

    public String getCurrentImage(){return this.currentImage;}
    public void setCurrentImage(String imageURL){
        this.currentImage = imageURL;
    }

    public Tiles getType(){return type;}
    public void setType(Tiles newType){this.type = newType;}

    public String getDefaultImage(){return  defaultImage;}
    public void setDefaultImage(String newImage){
        this.defaultImage = newImage;
    }
}
