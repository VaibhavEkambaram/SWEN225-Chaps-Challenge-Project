package nz.ac.vuw.ecs.swen225.gp23.maze;

import javax.json.JsonReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for the tiles in chap's challenge.
 * Each tile is differentiated by its own sub class.
 *
 * @author Baxter Kirikiri
 */
public abstract class Tile {
    public boolean isPassable;
    public boolean hasEntity;
    public Directions entityDirection;
    public int yLoc;
    public int xLoc;
    public String currentImage; //for imageURL
    public String defaultImage;
    public Tiles type;
    public List<Tile> adjacentTiles = new ArrayList<>();

    /**
     * Possible types of tiles
     */
    public enum Tiles {
        Empty, Floor, Wall, LockedDoor, Key, Exit, ExitLock, ComputerChip, Hint
    }

    /**
     * Possible directions for chap (the player) to be facing
     */
    public enum Directions {
        Left, Right, Up, Down;

        public Directions reverse(){
            switch(this){
                case Left:
                    return Right;
                case Right:
                    return Left;
                case Up:
                    return Down;
                default:
                    return Up;
            }
        }
    }

    /**
     * Constructor for tiles
     *
     * @param t - type of tile (Tile.Tiles)
     */
    public Tile(Tiles t){
        this.type = t;
    }

    /**
     * Displays an entity to the tile.
     * (e.g. when the player walks on to the tile, the players image should be the parameter)
     *
     * @param image - the image of the entity occupying this tile (String)
     */
    public void setEntityPresent(String image){
        this.currentImage = image;
        if(image.startsWith("cyclops")){
            this.hasEntity = true;
        }
    }

    public void setEntityDirection(Directions d){
        this.entityDirection = d;
    }
    public Directions getEntityDirection(){
        return this.entityDirection;
    }

    /**
     * Removes an entity from the tile by resetting it to the original image.
     * (e.g. when the player walks off the tile).
     */
    public void setEntityAbsent(){
        this.currentImage = this.defaultImage;
        this.hasEntity = false;
    }

    /**
     * Gets the string of tile in it's current state.
     *
     * @return The current image being displayed on the tile with the .png excluded (String)
     */
    public String toString(){
        return currentImage.substring(0, currentImage.length()-4);
    }

    /**
     * Validates whether the player can complete the requested action.
     * (e.g. the player tries to move onto a wall tile, action should return false)
     * This validation will vary depending on the tile type.
     *
     * @param p - the player (Player)
     * @return - true if the player can complete the action (boolean)
     */
    public abstract boolean action(Player p);

    /**
     * Gets the adjacent tile in a given direction.
     *
     * @param d - direction (Tile.Directions)
     * @return - tile in the given direction (Tile)
     */
    public Tile getDirection(Directions d){
        if (adjacentTiles.size() < 4) {
            return null;
        } else {
            return adjacentTiles.get(d.ordinal());
        }
    }

    /**
     * Gets the x location of the tile.
     *
     * @return - the x location of the tile (int)
     */
    public int getXLoc(){return this.xLoc;}

    /**
     * Sets the x location of the tile.
     *
     * @param xLoc - the new x location of the tile (int)
     */
    public void setXLoc(int xLoc){
        this.xLoc = xLoc;
    }

    /**
     * Gets the y location of the tile.
     *
     * @return - the y location of the tile (int)
     */
    public int getYLoc(){return this.yLoc;}

    /**
     * Sets the y location of the tile.
     *
     * @param yLoc - the new y location of the tile (int)
     */
    public void setYLoc(int yLoc){
        this.yLoc = yLoc;
    }

    /**
     * Gets the current image displayed on the tile.
     *
     * @return - the current image displayed on the tile (string)
     */
    public String getCurrentImage(){return this.currentImage;}
}
