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
        Left, Right, Up, Down
    }

    /**
     * Constructor for tiles
     *
     * @param t - type of tile
     */
    public Tile(Tiles t){
        this.type = t;
    }

    /**
     * Displays an entity to the tile.
     * (e.g. when the player walks on to the tile, the players image should be the parameter)
     *
     * @param image - the image of the entity occupying this tile
     */
    public void setEntityPresent(String image){
        this.currentImage = image;
    }

    /**
     * Removes an entity from the tile by resetting it to the original image.
     * (e.g. when the player walks off the tile).
     */
    public void setEntityAbsent(){
        this.currentImage = this.defaultImage;
    }

    /**
     * Gets the string of tile in it's current state.
     *
     * @return The current image being displayed on the tile (with the .png excluded)
     */
    public String toString(){
        return currentImage.substring(0, currentImage.length()-4);
    }

    /**
     * Validates whether the player can complete the requested action.
     * (e.g. the player tries to move onto a wall tile, action should return false)
     * This validation will vary depending on the tile type.
     *
     * @param p - the player
     * @return - true if the player can complete the action
     */
    public abstract boolean action(Player p);

    /**
     * Gets the adjacent tile in a given direction.
     *
     * @param d - direction
     * @return - tile in the given direction
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
     * @return - the x location of the tile
     */
    public int getXLoc(){return this.xLoc;}

    /**
     * Sets the x location of the tile.
     *
     * @param xLoc - the new x location of the tile
     */
    public void setXLoc(int xLoc){
        this.xLoc = xLoc;
    }

    /**
     * Gets the y location of the tile.
     *
     * @return - the y location of the tile
     */
    public int getYLoc(){return this.yLoc;}

    /**
     * Sets the y location of the tile.
     *
     * @param yLoc - the new y location of the tile
     */
    public void setYLoc(int yLoc){
        this.yLoc = yLoc;
    }

    /**
     * Gets the current image displayed on the tile.
     *
     * @return - the current image displayed on the tile
     */
    public String getCurrentImage(){return this.currentImage;}
}
