package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents an empty tile in the game (can be used to show the background within a level).
 *
 * @author Baxter Kirikiri - 300472553
 */
public class Empty extends Tile {

    /**
     * Constructor for Empty.
     * Completes necessary setup for an empty tile.
     *
     */
    public Empty(){
        super(Tiles.Empty);
        this.isPassable = false;
        this.currentImage = "empty.png";
        this.defaultImage = "empty.png";
    }

    /**
     * Validates whether the player can travel through this tile.
     *
     * @param p - the player (Player)
     * @return isPassable - should always be false for an empty tile because the player is not intended to access them (Boolean)
     */
    @Override
    public boolean action(Player p) {return isPassable;}
}
